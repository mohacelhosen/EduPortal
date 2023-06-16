package com.mohacel.edu.service;

import com.mohacel.edu.dto.CompleteUserDto;
import com.mohacel.edu.dto.GuardianAddress;
import com.mohacel.edu.model.*;
import com.mohacel.edu.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService{

    private CompleteUserRepository completeUserRepository;
    private EmergencyContactRepository emergencyContactRepository;
    private ExtraCurricularRepository extraCurricularRepository;
    private GuardianAddressRepository guardianAddressRepository;
    private GuardianInformationRepository guardianInformationRepository;
    private MedicalInformationRepository medicalInformationRepository;
    private MedicalEmergencyContactRepository medicalEmergencyContactRepository;
    private UserAddressRepository userAddressRepository;

    public UserServiceImpl(CompleteUserRepository completeUserRepository,
                           EmergencyContactRepository emergencyContactRepository,
                           ExtraCurricularRepository extraCurricularRepository,
                           GuardianAddressRepository guardianAddressRepository,
                           GuardianInformationRepository guardianInformationRepository,
                           MedicalInformationRepository medicalInformationRepository,
                           MedicalEmergencyContactRepository medicalEmergencyContactRepository,
                           UserAddressRepository userAddressRepository) {
        this.completeUserRepository = completeUserRepository;
        this.emergencyContactRepository = emergencyContactRepository;
        this.extraCurricularRepository = extraCurricularRepository;
        this.guardianAddressRepository = guardianAddressRepository;
        this.guardianInformationRepository = guardianInformationRepository;
        this.medicalInformationRepository = medicalInformationRepository;
        this.medicalEmergencyContactRepository = medicalEmergencyContactRepository;
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public boolean registerUser(CompleteUserDto completeUserDto) {
        CompleteUserEntity completeUser = new CompleteUserEntity();
        UserAddressEntity userAddress = new UserAddressEntity();
        EmergencyContactEntity emergencyContact = new EmergencyContactEntity();
        ExtracurricularEntity extracurricular = new ExtracurricularEntity();
        GuardianAddressEntity guardianAddress = new GuardianAddressEntity();
        GuardianInformationEntity guardianInformation = new GuardianInformationEntity();
        MedicalInformationEntity medicalInformation = new MedicalInformationEntity();
        MedicalEmergencyContactEntity medicalEmergencyContact = new MedicalEmergencyContactEntity();
        String password = generateRandomString(8);
        completeUser.setPassword(password);
        try{
            BeanUtils.copyProperties(completeUserDto, completeUser);
            BeanUtils.copyProperties(completeUserDto,userAddress);
            BeanUtils.copyProperties(completeUserDto, emergencyContact);
            BeanUtils.copyProperties(completeUserDto, extracurricular);
            BeanUtils.copyProperties(completeUserDto, guardianAddress);
            BeanUtils.copyProperties(completeUserDto, guardianInformation);
            BeanUtils.copyProperties(completeUserDto,medicalInformation);
            BeanUtils.copyProperties(completeUserDto,medicalEmergencyContact);


            Integer id = completeUserRepository.save(completeUser).getId();
            userAddressRepository.save(userAddress);
            guardianAddressRepository.save(guardianAddress);
            guardianInformationRepository.save(guardianInformation);
            emergencyContactRepository.save(emergencyContact);
            extraCurricularRepository.save(extracurricular);
            medicalInformationRepository.save(medicalInformation);
            medicalEmergencyContactRepository.save(medicalEmergencyContact);
            if(id!=null){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public CompleteUserDto findUserById(Integer userId) {
        return null;
    }

    @Override
    public boolean deleteUserById(Integer userId) {
        return false;
    }

    @Override
    public String updateUserInfo(Integer userId, CompleteUserDto completeUserDto) {
        return null;
    }

    @Override
    public List<CompleteUserDto> getAllUser() {
        return null;
    }


    // to generate random password
    private String generateRandomString(int length) {
        final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
