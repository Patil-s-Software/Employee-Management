package com.employeemanagement.employeemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.UserDto;
import com.employeemanagement.employeemanagement.entity.User;
import com.employeemanagement.employeemanagement.entity.Role;
import com.employeemanagement.employeemanagement.exception.DuplicateEntry;
import com.employeemanagement.employeemanagement.exception.NotFoundException;
import com.employeemanagement.employeemanagement.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo employeeRepo;

    public List<User> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public UserDto creaEmployee(UserDto employeeDto){
        User employee = new User();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(employeeDto.getPassword());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setProfilePic(employeeDto.getProfilePic());

        if(employeeRepo.findByEmail(employeeDto.getEmail()).isPresent()){
            throw new DuplicateEntry("Email " + employeeDto.getEmail());
        }
        employeeRepo.save(employee);

        return employeeDto;
    }

    public String deleteEmployee(Integer id){
        if (employeeRepo.findById(id).isPresent()) {
            employeeRepo.deleteById(id);
            return "deleted employee by id: " + id;
        }else{
            throw new NotFoundException("Employee not found with id: " + id);
        }
    }

    public UserDto updateEmployee(UserDto employeeDto, Integer id){
        User employee = employeeRepo.findById(id).orElseThrow(()-> new NotFoundException("Employee not found with id: " + id));
        if (employeeDto.getName() != null) employee.setName(employeeDto.getName());
        if(employeeDto.getAddress() != null) employee.setAddress(employeeDto.getAddress());
        if(employeeDto.getDepartment() != null) employee.setDepartment(employeeDto.getDepartment());
        if (employeeDto.getAddress() != null) employee.setDesignation(employeeDto.getDesignation());
        if (employeeDto.getProfilePic() != null) employee.setProfilePic(employeeDto.getProfilePic());
        employeeRepo.save(employee);
        return employeeDto;
    }
}
