package com.mohacel.edu.service;

import com.mohacel.edu.dto.CompleteUserDto;
import com.mohacel.edu.dto.GuardianAddress;
import com.mohacel.edu.exception.RegistrationFailException;
import com.mohacel.edu.model.*;
import com.mohacel.edu.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
            BeanUtils.copyProperties(completeUserDto.getUserAddress(),userAddress);
            System.out.println(userAddress);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            BeanUtils.copyProperties(completeUserDto.getEmergencyContact(), emergencyContact);
            System.out.println(emergencyContact);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            BeanUtils.copyProperties(completeUserDto.getExtracurricular(), extracurricular);
            System.out.println(extracurricular);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            BeanUtils.copyProperties(completeUserDto.getGuardianInformation().getGuardianAddress(), guardianAddress);
            System.out.println(guardianAddress);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            BeanUtils.copyProperties(completeUserDto.getGuardianInformation(), guardianInformation);
            System.out.println(guardianInformation);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            BeanUtils.copyProperties(completeUserDto.getMedicalInformation(),medicalInformation);
            System.out.println(medicalInformation);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            BeanUtils.copyProperties(completeUserDto.getMedicalEmergencyContact(),medicalEmergencyContact);
            System.out.println(medicalEmergencyContact);
            System.out.println("----------------------------------------------------------------------------------------------------------------");


            userAddressRepository.save(userAddress);
            guardianAddressRepository.save(guardianAddress);
            guardianInformationRepository.save(guardianInformation);
            emergencyContactRepository.save(emergencyContact);
            extraCurricularRepository.save(extracurricular);
            medicalInformationRepository.save(medicalInformation);
            medicalEmergencyContactRepository.save(medicalEmergencyContact);
            Integer id = completeUserRepository.save(completeUser).getId();
            if(id!=null){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RegistrationFailException(e.getMessage());
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
