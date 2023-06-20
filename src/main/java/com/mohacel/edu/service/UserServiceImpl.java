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
                copyProperties(dtoUserAddress,userAddress);
                copyProperties(dtoEmergencyContact, emergencyContact);
                copyProperties(dtoExtracurricular, extracurricular);
                copyProperties(dtoGuardianAddress, guardianAddress);
                copyProperties(dtoGuardianInformation, guardianInformation);
                copyProperties(dtoMedicalInformation,medicalInformation);
                copyProperties(dtoMedicalEmergencyContact,medicalEmergencyContact);


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

//            userAddressRepository.save(userAddress);
//            guardianAddressRepository.save(guardianAddress);
//            guardianInformationRepository.save(guardianInformation);
//            emergencyContactRepository.save(emergencyContact);
//            extraCurricularRepository.save(extracurricular);
//            medicalInformationRepository.save(medicalInformation);
//            medicalEmergencyContactRepository.save(medicalEmergencyContact);
            Integer id = completeUserRepository.save(completeUser).getId();



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


            return id != null;
        }catch (Exception e){
            throw new RegistrationFailException(e.getMessage());
        }
    }

    @Override
    public CompleteUserDto findUserById(Integer userId) {
        Optional<CompleteUserEntity> completeUserInfoById = completeUserRepository.findById(userId);
        if (completeUserInfoById.isPresent()) {
            CompleteUserEntity completeUser = completeUserInfoById.get();
            CompleteUserDto completeUserDto = new CompleteUserDto();

            UserAddress userAddress = new UserAddress();
            copyProperties(completeUser.getUserAddress(), userAddress);

            Extracurricular extracurricular = new Extracurricular();
            copyProperties(completeUser.getExtracurricular(),extracurricular);

            EmergencyContact emergencyContact = new EmergencyContact();
            copyProperties(completeUser.getEmergencyContact(), emergencyContact);

            GuardianAddress guardianAddress = new GuardianAddress();
            GuardianInformation guardianInformation = new GuardianInformation();
            copyProperties(completeUser.getGuardianInformation().getGuardianAddress(),guardianAddress);
            copyProperties(completeUser.getGuardianInformation(), guardianInformation);
            guardianInformation.setGuardianAddress(guardianAddress);

            MedicalInformation medicalInformation = new MedicalInformation();
            copyProperties(completeUser.getMedicalInformation(), medicalInformation);

            MedicalEmergencyContact medicalEmergencyContact = new MedicalEmergencyContact();
            copyProperties(completeUser.getMedicalEmergencyContact(), medicalEmergencyContact);


            completeUserDto.setUserId(completeUser.getUserId());
            completeUserDto.setFullName(completeUser.getFullName());
            completeUserDto.setEmail(completeUser.getEmail());
            completeUserDto.setDob(completeUser.getDob());
            completeUserDto.setGender(completeUser.getGender());
            completeUserDto.setHeight(completeUser.getHeight());
            completeUserDto.setWeight(completeUser.getWeight());
            completeUserDto.setUserContactNumber(completeUser.getUserContactNumber());
            completeUserDto.setUserNationality(completeUser.getUserNationality());
            completeUserDto.setAcademicInterests(completeUser.getAcademicInterests());
            completeUserDto.setExtracurricular(extracurricular);
            completeUserDto.setEmergencyContact(emergencyContact);
            completeUserDto.setGuardianInformation(guardianInformation);
            completeUserDto.setMedicalInformation(medicalInformation);
            completeUserDto.setMedicalEmergencyContact(medicalEmergencyContact);

            return completeUserDto;
        } else {
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

    public static <S, T> void copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
    }
}
