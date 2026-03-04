package com.finapp.finance_calculator.tnc;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.finapp.finance_calculator.dto.ConsentRequestDTO;
import com.finapp.finance_calculator.repo.PublicConsentRepository;
import com.finapp.finance_calculator.vo.PublicConsentLogVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Service
public class ConsentSVC {

	private final PublicConsentRepository repository;

	public ConsentSVC(PublicConsentRepository repository) {
		this.repository = repository;
	}

	/**
	 * @param requestDTO
	 * @param httpRequest
	 * @param httpResponse
	 */
	public void saveConsent(ConsentRequestDTO requestDTO, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		PublicConsentLogVO log = new PublicConsentLogVO();

		log.setSessionId(httpRequest.getSession().getId());
		log.setConsentType(requestDTO.getConsentType());
		log.setVersion(requestDTO.getVersion());
		log.setContentHash(generateHash(requestDTO.getContent()));
		log.setAgreedAt(LocalDateTime.now());
		log.setUserAgent(httpRequest.getHeader("User-Agent"));
		String ip = getClientIp(httpRequest);
		Map<String, Object> geoData = getLocationFromIp(ip);
		log.setIpAddress(ip);
		log.setLatitude(String.valueOf(geoData.get("lat")));
		log.setLongitude(String.valueOf(geoData.get("lon")));
		log.setAnonId(getOrCreateAnonymousId(httpRequest, httpResponse));

		repository.save(log);
	}	

	/**
	 * @param content
	 * @return String
	 */
	private String generateHash(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(content.getBytes());
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			throw new RuntimeException("Error generating hash", e);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private String getClientIp(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

	/**
	 * @param ip
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getLocationFromIp(String ip) {
		String url = "http://ip-api.com/json/" + ip;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Map.class);
	}

	/**
	 * @param consentType
	 * @param version
	 * @param request
	 * @return boolean
	 */
	public boolean hasConsent(String consentType, String version, HttpServletRequest request, HttpServletResponse response) {
		String anonId = getOrCreateAnonymousId(request, response);
		System.out.println(anonId);
		System.out.println(repository.existsByAnonidAndConsentTypeAndVersion(anonId, consentType, version));
		return repository.existsByAnonidAndConsentTypeAndVersion(anonId, consentType, version);
	}

	/**
	 * @param request
	 * @param response
	 * @return String
	 */
	private String getOrCreateAnonymousId(HttpServletRequest request, HttpServletResponse response) {

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("ANON_ID".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}

		String anonId = UUID.randomUUID().toString();
		Cookie cookie = new Cookie("ANON_ID", anonId);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 * 90);
		cookie.setPath("/");

		response.addCookie(cookie);

		return anonId;
	}
}