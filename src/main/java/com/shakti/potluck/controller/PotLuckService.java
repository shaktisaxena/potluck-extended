package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import com.shakti.potluck.entity.PotLuckDetails;
import com.shakti.potluck.entity.PotLuckDto;
import com.shakti.potluck.entity.User;
import com.shakti.potluck.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PotLuckService {

    private final PotLuckDao _potLuckDao;
    private final ModelMapper mapper;
    private final UserDao userDao;
    private final CustomUserDetailsService userService;


    public void createPotLuck(PotLuckDto potLuckDto) {
        checkIfPotLuckNameExists(potLuckDto.getName());
        PotLuck potLuck = mapper.map(potLuckDto, PotLuck.class);
        User user = getLoggedInUser();
        potLuck.setUserEmail(user.getEmail());
        _potLuckDao.save(potLuck);
    }

    private void checkIfPotLuckNameExists(String name) {
        _potLuckDao.findByName(name)
                .ifPresent(value ->
                {
                    log.error("Potluck with name is already present. : {}", value);
                    throw new IllegalStateException("found");
                });
    }

    public PotLuckDetails findPotLuckByName(String potLuckName) {
        User user = getLoggedInUser();
        return mapper.map(_potLuckDao.findByNameAndUserEmail(potLuckName, user.getEmail())
                .orElse(new PotLuck()), PotLuckDetails.class);
    }

    public void updatePotLuck(PotLuckDto potLuckDto) {
        User user = getLoggedInUser();
        PotLuck potLuck = _potLuckDao.findByNameAndUserEmail(potLuckDto.getName(), user.getEmail()).orElseThrow(ResourceNotFoundException::new);
        potLuckDto.setId(potLuck.getId());
        potLuckDto.setName(potLuck.getName());
        potLuckDto.setUserEmail(potLuck.getUserEmail());
        _potLuckDao.update(mapper.map(potLuckDto, PotLuck.class));
    }

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }

    public void deletePotLuck(String id) {
        _potLuckDao.delete(_potLuckDao.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public boolean existPotLuckByName(String potLuckName) {
        return _potLuckDao.existsPotluckByName(potLuckName);
    }


    public List<PotLuckDto> findAllPotLuck() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return _potLuckDao.listsAllPotLuckByEmail(userDetails.getUsername()).get().
                stream()
                .map(potLuck -> {
                    PotLuckDto dto=mapper.map(potLuck, PotLuckDto.class);
                    return dto;

                }).collect(Collectors.toList());

    }
}
