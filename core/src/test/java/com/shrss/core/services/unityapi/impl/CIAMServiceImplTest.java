
// package com.shrss.core.services.unityapi.impl;


// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;

// import org.apache.http.client.fluent.Executor;
// import org.apache.http.client.fluent.Request;
// import org.apache.http.client.fluent.Response;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.google.gson.Gson;
// import com.shrss.core.services.unityapi.HttpClientFactory;
// import com.shrss.core.services.unityapi.StringObjectResponseHandler;
// import com.shrss.core.services.unityapi.pojo.CIAMTokenResponse;

// @ExtendWith(MockitoExtension.class)
// public class CIAMServiceImplTest {

//     @Mock
//     private HttpClientFactory httpClientFactory;

//     @Mock
//     private Executor executor;

//     @Mock
//     private Response response;

//     @InjectMocks
//     private CIAMServiceImpl ciamService;

//     private CIAMServiceConfig config;

//     @BeforeEach
//     public void setUp() throws Exception {
//         config = mock(CIAMServiceConfig.class);
//         when(config.ciamHostName()).thenReturn("https://unity.qa.login.hardrock.com");
//         when(config.tokenPath()).thenReturn("/oauth2/token");
//         when(config.logouturi()).thenReturn("/oidc/logout");
//         when(config.extendedSession()).thenReturn("/identity/extend-session");
//         when(config.guestAPIClientId()).thenReturn("guestClientId");
//         when(config.guestAPIClientSecret()).thenReturn("guestClientSecret");

//         // Ensure that the httpClientFactory is properly mocked
//         when(httpClientFactory.getExecutor()).thenReturn(executor);
//         when(executor.auth("guestClientId", "guestClientSecret")).thenReturn(executor);
//         when(executor.execute(any(Request.class))).thenReturn(response);
//         when(response.handleResponse(any(StringObjectResponseHandler.class))).thenReturn("{\"access_token\":\"mockToken\",\"expires_in\":3600}");

//         ciamService.activate(config);
//     }

//     @Test
//     public void testGetGuestCIAMTokenService() throws IOException {
//         String mockResponse = "{\"access_token\":\"mockToken\",\"expires_in\":3600}";
//         when(httpClientFactory.getExecutor()).thenReturn(executor);
//         when(executor.auth("guestClientId", "guestClientSecret")).thenReturn(executor);
//         when(executor.execute(any(Request.class))).thenReturn(response);
//         when(response.handleResponse(any(StringObjectResponseHandler.class))).thenReturn(mockResponse);

//         String result = ciamService.getGuestCIAMTokenService();

//         assertNotNull(result);
//         assertEquals(mockResponse, result);

//         CIAMTokenResponse cachedToken = ciamService.getCachedGuestToken();
//         assertNotNull(cachedToken);
//         assertEquals("mockToken", cachedToken.getAccessToken());
//     }

//     @Test
//     public void testBuildURL() {
//         String apiEndpointURI = "https://unity.qa.login.hardrock.com/oauth2/token";
//         Map<String, String> params = new HashMap<>();
//         params.put("grant_type", "client_credentials");
//         params.put("scope", "device_adobe1");

//         String result = ciamService.buildURL(apiEndpointURI, params);

//         assertEquals("https://unity.qa.login.hardrock.com/oauth2/token?grant_type=client_credentials&scope=device_adobe1", result);
//     }

//     @Test
//     public void testGetCIAMSessionService() throws IOException {
//         String idpSessionKey = "mockSessionKey";
//         String mockResponse = "{\"session_key\":\"mockSessionKey\"}";

//         // Mock the HTTP client and response
//         when(httpClientFactory.getExecutor()).thenReturn(executor);
//         when(executor.auth("guestClientId", "guestClientSecret")).thenReturn(executor);
//         when(executor.execute(any(Request.class))).thenReturn(response);
//         when(response.handleResponse(any(StringObjectResponseHandler.class))).thenReturn(mockResponse);

//         // Call the method under test
//         String result = ciamService.getCIAMSessionService(idpSessionKey);

//         // Verify the result
//         assertNotNull(result);
//         assertEquals(mockResponse, result);
//     }

//     @Test
//     public void testGetCachedGuestToken() {
//         // Ensure that the cached token is null initially
//         assertNull(ciamService.getCachedGuestToken());

//         // Set a mock cached token
//         CIAMTokenResponse mockToken = new CIAMTokenResponse();
//         mockToken.setAccessToken("mockToken");
//         ciamService.guestCachedToken = mockToken;

//         CIAMTokenResponse cachedToken = ciamService.getCachedGuestToken();
//         assertNotNull(cachedToken);
//         assertEquals("mockToken", cachedToken.getAccessToken());
//     }

//     @Test
//     public void testGetCIAMLogoutService() {
//         // This method is not implemented, so it should return null
//         assertNull(ciamService.getCIAMLogoutService());
//     }

//     @Test
//     public void testGetClientCIAMTokenService() {
//         // This method is not implemented, so it should return null
//         assertNull(ciamService.getClientCIAMTokenService());
//     }

//     @Test
//     public void testGetCIAMTokenFromRefreshToken()     {
//         // This method is not implemented, so it should return null
//         assertNull(ciamService.getCIAMTokenFromRefreshToken());
//     }
// }
