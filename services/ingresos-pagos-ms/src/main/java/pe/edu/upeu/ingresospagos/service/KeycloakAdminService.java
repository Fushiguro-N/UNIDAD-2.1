package pe.edu.upeu.ingresospagos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakAdminService {

    private final RestClient restClient;
    private final String realm;
    private final String clientId;
    private final String clientSecret;

    public KeycloakAdminService(
            @Value("${app.keycloak.admin.server-url}") String serverUrl,
            @Value("${app.keycloak.admin.realm}") String realm,
            @Value("${app.keycloak.admin.client-id}") String clientId,
            @Value("${app.keycloak.admin.client-secret}") String clientSecret) {
        this.restClient = RestClient.create(serverUrl);
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /** Crea un usuario en Keycloak con rol ESTUDIANTE y contrasena temporal. Devuelve el UUID generado. */
    public String crearUsuarioEstudiante(String username, String email, String nombres, String apellidos, String temporaryPassword) {
        String token = obtenerTokenAdmin();

        Map<String, Object> credencial = Map.of(
                "type", "password",
                "value", temporaryPassword,
                "temporary", true);

        Map<String, Object> usuario = Map.of(
                "username", username,
                "email", email,
                "firstName", nombres,
                "lastName", apellidos,
                "enabled", true,
                "emailVerified", false,
                "credentials", List.of(credencial));

        URI location = restClient.post()
                .uri("/admin/realms/{realm}/users", realm)
                .header("Authorization", "Bearer " + token)
                .body(usuario)
                .exchange((req, res) -> {
                    if (res.getStatusCode().value() == 409) {
                        throw new IllegalArgumentException("Ya existe un usuario con ese username o correo en Keycloak");
                    }
                    if (!res.getStatusCode().is2xxSuccessful()) {
                        throw new IllegalStateException("Error creando usuario en Keycloak: " + res.getStatusCode());
                    }
                    return res.getHeaders().getLocation();
                });

        if (location == null) {
            throw new IllegalStateException("Keycloak no devolvio la ubicacion del usuario creado");
        }
        String path = location.getPath();
        String userId = path.substring(path.lastIndexOf('/') + 1);

        asignarRolEstudiante(token, userId);
        return userId;
    }

    private void asignarRolEstudiante(String token, String userId) {
        Map<String, Object> rol = restClient.get()
                .uri("/admin/realms/{realm}/roles/{role}", realm, "ESTUDIANTE")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(Map.class);

        restClient.post()
                .uri("/admin/realms/{realm}/users/{userId}/role-mappings/realm", realm, userId)
                .header("Authorization", "Bearer " + token)
                .body(List.of(rol))
                .exchange((req, res) -> {
                    if (!res.getStatusCode().is2xxSuccessful()) {
                        throw new IllegalStateException("Error asignando rol ESTUDIANTE en Keycloak: " + res.getStatusCode());
                    }
                    return null;
                });
    }

    private String obtenerTokenAdmin() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);

        Map<String, Object> response = restClient.post()
                .uri("/realms/{realm}/protocol/openid-connect/token", realm)
                .contentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(Map.class);

        if (response == null || response.get("access_token") == null) {
            throw new IllegalStateException("No se pudo obtener token de administrador de Keycloak");
        }
        return (String) response.get("access_token");
    }
}
