package pe.edu.upeu.lms.util;

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

    /** true only when the caller has ESTUDIANTE and neither ADMIN nor DOCENTE. */
    public static boolean isSoloEstudiante(Jwt jwt) {
        List<String> roles = rolesOf(jwt);
        return roles.contains("ESTUDIANTE") && !roles.contains("ADMIN") && !roles.contains("DOCENTE");
    }
}
