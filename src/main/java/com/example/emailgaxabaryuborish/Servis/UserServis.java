package com.example.emailgaxabaryuborish.Servis;

import antlr.Token;
import com.example.emailgaxabaryuborish.Entity.Enum.Lavozimlar;
import com.example.emailgaxabaryuborish.Entity.Users;
import com.example.emailgaxabaryuborish.Payloat.ApiResponsive;
import com.example.emailgaxabaryuborish.Payloat.LoginDTO;
import com.example.emailgaxabaryuborish.Payloat.UserDTO;
import com.example.emailgaxabaryuborish.Repository.LavozimRepository;
import com.example.emailgaxabaryuborish.Repository.UserRepository;
import com.example.emailgaxabaryuborish.Token.Tokenn;
import com.example.emailgaxabaryuborish.XafsizlikSozlamalari.XafsizlikSozlamalari;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.UUID;

import static com.example.emailgaxabaryuborish.Entity.Enum.Lavozimlar.USER;

@Service
public class UserServis implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    XafsizlikSozlamalari xafsizlikSozlamalari;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    Tokenn tokenn;

    public ApiResponsive postUser(UserDTO userDTO) {
        boolean b = userRepository.existsByUsername(userDTO.getUsername());
        if (b) return new ApiResponsive("Bunday foydalanuvchi allaqachon ruyxatdan utgan!!!", false);
        Users users = new Users();
        users.setIsmi(userDTO.getIsmi());
        users.setFamilyasi(userDTO.getFamilyasi());
        users.setTelRaqam(userDTO.getTelRaqam());
        users.setUsername(userDTO.getUsername());
        users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        users.setLavozimList(lavozimRepository.findByLavozimlar(USER));
        String code=UUID.randomUUID().toString().substring(0, 6);
        users.setEmailKod(code);  // bu yerda uzunligi 6ga teng bo'lgan takrorlanmaydigan kod yasab berildi
        if (xabarYuborish(userDTO.getUsername(), code)){
            userRepository.save(users);
            return new ApiResponsive("Muffaqaiyatli ruyxatdan o'tdingiz akkountni faollashtirish kodi emailingizga yuborildi!!!",true);
        }
        return new ApiResponsive("Malumotlaringizni qaytadan tekshirib ruyxatdan o'tib ko'ring!!!", false);
    }
    public boolean xabarYuborish(String email, String emailkod){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("hondamirkobilov02@gmail.com");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Tasdiqlash linki!!!");
            simpleMailMessage.setText("<a href='http://localhost:8080/userAPI/tasdiqlash?email="+email+"&emailkod="+emailkod+"'>Tasdiqlash linki</a>");
            javaMailSender.send(simpleMailMessage);
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }
    public ApiResponsive getTasdiqlash(String email, String emailkod) {
        System.out.println(email+" "+emailkod);
        Optional<Users> byUsernameAndEmailKod = userRepository.findByUsernameAndEmailKod(email, emailkod);
        if (byUsernameAndEmailKod.isPresent()){
            Users users = byUsernameAndEmailKod.get();
            users.setEnabled(true);
            users.setEmailKod(null);
            userRepository.save(users);
            return new ApiResponsive("Accountingiz faollashtirildi!!!",true);
        }
        return new ApiResponsive("Allaqachon faollashtirilgan!!!",false);
    }

    public ApiResponsive loginPost(LoginDTO loginDTO) {
        boolean b = userRepository.existsByUsernameAndEmailKod(loginDTO.getUsername(), null);
        if (b){
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            if (authenticate.isAuthenticated()){ //bu shart login parol tugrimi deb tekshiradi
                Users principal = (Users) authenticate.getPrincipal();
                return new ApiResponsive("Profilga xush kelibsiz!!!"+tokenn.tokenOlish(principal.getUsername()),true);
            }
            return new ApiResponsive("Login yoki parol xato",false);
        }
        return new ApiResponsive("Accountingiz faollashtirilmagan!!!",false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()){
            return byUsername.get();
        }
        throw new UsernameNotFoundException("Bunday foydalanuvchi topilmadi!!!");
    }
}