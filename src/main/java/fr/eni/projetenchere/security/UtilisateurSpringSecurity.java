package fr.eni.projetenchere.security;

import fr.eni.projetenchere.bo.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurSpringSecurity implements UserDetails {

    private Utilisateur utilisateur;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(utilisateur.isAdministrateur()){
            return List.of(new SimpleGrantedAuthority("ROLE_admin"));

        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_user"));
        }

    }

    @Override
    public @Nullable String getPassword() {

        return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return utilisateur.getUsername();
    }

    public long getUserId() {

        return utilisateur.getIdUtilisateur();
    }
}
