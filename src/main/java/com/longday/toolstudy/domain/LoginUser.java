package com.longday.toolstudy.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.longday.toolstudy.enums.CommonConst.DISABLE;
import static com.longday.toolstudy.enums.CommonConst.EXPIRED;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/30 13:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIncludeProperties({"fdUser","authorityList"})
public class LoginUser implements UserDetails {

    private FdUser fdUser;

    private List<String> authorityList;

    private Set<SimpleGrantedAuthority> authoritiesSet;

    public LoginUser(FdUser fdUser, List<String> authorityList) {
        this.fdUser = fdUser;
        this.authorityList = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authoritiesSet!=null){
            return authoritiesSet;
        }
        authoritiesSet = authorityList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return authoritiesSet;
    }

    @Override
    public String getPassword() {
        return fdUser.getUserPassword();
    }

    @Override
    public String getUsername() {
        return fdUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !EXPIRED.equals(fdUser.getUserStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !DISABLE.equals(fdUser.getUserStatus());
    }
}
