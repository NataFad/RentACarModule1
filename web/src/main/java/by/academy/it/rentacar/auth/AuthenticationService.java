//package by.academy.it.rentacar.auth;
//
//import by.academy.it.rentacar.actions.IUserService;
//import by.academy.it.rentacar.entity.UserEntity;
//import by.academy.it.rentacar.enums.TypeUser;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("authService")
//public class AuthenticationService implements UserDetailsService {
//
//    private static Logger log = Logger.getLogger(AuthenticationService.class);
//    @Autowired
//    private IUserService userService;
//
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        UserEntity user = userService.getUserByLogin(login);
//        System.out.println("User : " + user);
//        if (user == null) {
//            System.out.println("User not found");
//            throw new UsernameNotFoundException("Username not found");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
//                true, true, true, true, getGrantedAuthorities(user));
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//        for (TypeUser userProfile : TypeUser.values()) {
//            log.debug("UserProfile : " + userProfile);
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getName()));
//        }
//        log.debug("authorities :" + authorities);
//        return authorities;
//    }
//
//}
