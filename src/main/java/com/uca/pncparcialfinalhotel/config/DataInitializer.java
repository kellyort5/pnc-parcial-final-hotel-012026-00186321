package com.uca.pncparcialfinalhotel.config;

import com.uca.pncparcialfinalhotel.entity.Hotel;
import com.uca.pncparcialfinalhotel.entity.Role;
import com.uca.pncparcialfinalhotel.entity.User;
import com.uca.pncparcialfinalhotel.enums.RoleName;
import com.uca.pncparcialfinalhotel.repository.HotelRepository;
import com.uca.pncparcialfinalhotel.repository.RoleRepository;
import com.uca.pncparcialfinalhotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role adminRole = createRoleIfNotExists(RoleName.ADMINISTRADOR);
        Role receptionistRole = createRoleIfNotExists(RoleName.RECEPCIONISTA);
        Role guestRole = createRoleIfNotExists(RoleName.HUESPED);

        Hotel hotel = createHotelIfNotExists();

        createUserIfNotExists(
                "admin",
                "admin123",
                "Administrador General",
                "admin@hotel.com",
                adminRole,
                null
        );

        createUserIfNotExists(
                "recepcion",
                "recepcion123",
                "Recepcionista Central",
                "recepcion@hotel.com",
                receptionistRole,
                hotel
        );

        createUserIfNotExists(
                "huesped",
                "huesped123",
                "Huésped de Prueba",
                "huesped@hotel.com",
                guestRole,
                null
        );
    }

    private Role createRoleIfNotExists(RoleName roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name(roleName)
                                .build()
                ));
    }

    private Hotel createHotelIfNotExists() {
        return hotelRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> hotelRepository.save(
                        Hotel.builder()
                                .name("Hotel Central")
                                .address("San Salvador")
                                .city("San Salvador")
                                .active(true)
                                .build()
                ));
    }

    private void createUserIfNotExists(
            String username,
            String password,
            String fullName,
            String email,
            Role role,
            Hotel hotel
    ) {
        if (!userRepository.existsByUsername(username)) {
            User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .fullName(fullName)
                    .email(email)
                    .role(role)
                    .hotel(hotel)
                    .build();

            userRepository.save(user);
        }
    }
}