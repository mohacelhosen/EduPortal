package com.mohacel.edu.service;

import com.mohacel.edu.dto.*;
import com.mohacel.edu.exception.RegistrationFailException;
import com.mohacel.edu.exception.UserIdNotFoundException;
import com.mohacel.edu.model.*;
import com.mohacel.edu.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService{

    private final CompleteUserRepository completeUserRepository;
    private final EmergencyContactRepository emergencyContactRepository;
    private final ExtraCurricularRepository extraCurricularRepository;
    private final GuardianAddressRepository guardianAddressRepository;
    private final GuardianInformationRepository guardianInformationRepository;
    private final MedicalInformationRepository medicalInformationRepository;
    private final MedicalEmergencyContactRepository medicalEmergencyContactRepository;
    private final UserAddressRepository userAddressRepository;

    @Autowired
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
        // create entity class object
        CompleteUserEntity completeUser = new CompleteUserEntity();
        UserAddressEntity userAddress = new UserAddressEntity();
        EmergencyContactEntity emergencyContact = new EmergencyContactEntity();
        ExtracurricularEntity extracurricular = new ExtracurricularEntity();
        GuardianAddressEntity guardianAddress = new GuardianAddressEntity();
        GuardianInformationEntity guardianInformation = new GuardianInformationEntity();
        MedicalInformationEntity medicalInformation = new MedicalInformationEntity();
        MedicalEmergencyContactEntity medicalEmergencyContact = new MedicalEmergencyContactEntity();

        String password = generateRandomString(8);
        try{
            // Copy CompleteUserDto data to CompleteUserEntity
            BeanUtils.copyProperties(completeUserDto, completeUser);
            completeUser.setPassword(password);

            // get the dto class's object data into a reference variable
            UserAddress dtoUserAddress = completeUserDto.getUserAddress();
            EmergencyContact dtoEmergencyContact = completeUserDto.getEmergencyContact();
            Extracurricular dtoExtracurricular = completeUserDto.getExtracurricular();
            GuardianAddress dtoGuardianAddress = completeUserDto.getGuardianInformation().getGuardianAddress();
            GuardianInformation dtoGuardianInformation = completeUserDto.getGuardianInformation();
            MedicalInformation dtoMedicalInformation = completeUserDto.getMedicalInformation();
            MedicalEmergencyContact dtoMedicalEmergencyContact = completeUserDto.getMedicalEmergencyContact();

            //check if the collected data isn't empty
            if (dtoUserAddress !=null &&
                    dtoEmergencyContact !=null &&
                    dtoExtracurricular !=null &&
                    dtoGuardianAddress !=null &&
                    dtoMedicalInformation !=null &&
                    dtoMedicalEmergencyContact !=null){

                //copy dto object to entity object with the
                BeanUtils.copyProperties(dtoUserAddress,userAddress);
                BeanUtils.copyProperties(dtoEmergencyContact, emergencyContact);
                BeanUtils.copyProperties(dtoExtracurricular, extracurricular);
                BeanUtils.copyProperties(dtoGuardianAddress, guardianAddress);
                BeanUtils.copyProperties(dtoGuardianInformation, guardianInformation);
                BeanUtils.copyProperties(dtoMedicalInformation,medicalInformation);
                BeanUtils.copyProperties(dtoMedicalEmergencyContact,medicalEmergencyContact);


                // Set the relationship between CompleteUserEntity and UserEntity
                completeUser.setUserAddress(userAddress);
                userAddress.setCompleteUser(completeUser);

                // Set the relationship between CompleteUserEntity and EmergencyEntity
                completeUser.setEmergencyContact(emergencyContact);


                // Set the relationship between CompleteUserEntity and ExtraCurricularEntity
                completeUser.setExtracurricular(extracurricular);


                // Set the relationship between CompleteUserEntity and GuardianInformationEntity
                //nested relationship
                guardianInformation.setGuardianAddress(guardianAddress);
                completeUser.setGuardianInformation(guardianInformation);


                // Set the relationship between CompleteUserEntity and MedicalInformationEntity
                completeUser.setMedicalInformation(medicalInformation);
                completeUser.setMedicalEmergencyContact(medicalEmergencyContact);



            }


            // print all the entity class data to verify whether they copy or not
            System.out.println(userAddress);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            System.out.println(emergencyContact);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            System.out.println(extracurricular);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            System.out.println(guardianAddress);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            System.out.println(guardianInformation);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            System.out.println(medicalInformation);
            System.out.println("----------------------------------------------------------------------------------------------------------------");

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
        // creating new object
        CompleteUserDto completeUserDto = new CompleteUserDto();
        UserAddress userAddress = new UserAddress();
        GuardianAddress guardianAddress = new GuardianAddress();
        GuardianInformation guardianInformation = new GuardianInformation();
        Extracurricular extracurricular = new Extracurricular();
        MedicalInformation medicalInformation = new MedicalInformation();
        MedicalEmergencyContact medicalEmergencyContact = new MedicalEmergencyContact();
        EmergencyContact emergencyContact = new EmergencyContact();

        Optional<CompleteUserEntity> completeUserInfoById = completeUserRepository.findById(userId);
        System.out.println("----------------------------------------------------------------------");
        System.out.println(completeUserInfoById);
        if (completeUserInfoById.isPresent()){
            //copy to new object
            BeanUtils.copyProperties(completeUserInfoById.get().getUserAddress(), userAddress);
            BeanUtils.copyProperties(completeUserInfoById.get().getGuardianInformation(), guardianInformation);
            BeanUtils.copyProperties(completeUserInfoById.get().getGuardianInformation().getGuardianAddress(), guardianAddress);
            BeanUtils.copyProperties(completeUserInfoById.get().getExtracurricular(), extracurricular);
            BeanUtils.copyProperties(completeUserInfoById.get().getMedicalInformation(), medicalInformation);
            BeanUtils.copyProperties(completeUserInfoById.get().getMedicalEmergencyContact(), medicalEmergencyContact);
            BeanUtils.copyProperties(completeUserInfoById.get().getEmergencyContact(), emergencyContact);
            guardianInformation.setGuardianAddress(guardianAddress);

            //set to the DTO class
            completeUserDto.setUserId(completeUserInfoById.get().getUserId());
            completeUserDto.setFullName(completeUserInfoById.get().getFullName());
            completeUserDto.setEmail(completeUserInfoById.get().getEmail());
            completeUserDto.setDob(completeUserInfoById.get().getDob());
            completeUserDto.setGender(completeUserInfoById.get().getGender());
            completeUserDto.setHeight(completeUserInfoById.get().getHeight());
            completeUserDto.setWeight(completeUserInfoById.get().getWeight());
            completeUserDto.setUserContactNumber(completeUserInfoById.get().getUserContactNumber());
            completeUserDto.setUserNationality(completeUserInfoById.get().getUserNationality());
            completeUserDto.setAcademicInterests(completeUserInfoById.get().getAcademicInterests());
            completeUserDto.setUserAddress(userAddress);
            completeUserDto.setExtracurricular(extracurricular);
            completeUserDto.setEmergencyContact(emergencyContact);
            completeUserDto.setGuardianInformation(guardianInformation);
            completeUserDto.setMedicalInformation(medicalInformation);
            completeUserDto.setMedicalEmergencyContact(medicalEmergencyContact);




            System.out.println("----------------------------------------------------------------------");
            System.out.println(completeUserDto);
            System.out.println("----------------------------------------------------------------------");
            return completeUserDto;
        }else{
         throw new UserIdNotFoundException("Invalid Id");
        }
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
