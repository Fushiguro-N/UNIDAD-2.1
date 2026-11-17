package pe.edu.upeu.ingresospagos.util;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

public final class JwtRoleUtils {

    private JwtRoleUtils() {
    }

    @SuppressWarnings("unchecked")
    public static List<String> rolesOf(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess == null || realmAccess.get("roles") == null) {
            return List.of();
        }
        return (List<String>) realmAccess.get("roles");
    }

    public static boolean isAdmin(Jwt jwt) {
        return rolesOf(jwt).contains("ADMIN");
    }
}
