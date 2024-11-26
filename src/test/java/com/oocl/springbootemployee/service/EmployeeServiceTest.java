package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.exception.EmployeeAgeNotValidException;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeInMemoryRepository;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeInMemoryRepository mockedEmployeeInMemoryRepository;

    @Mock
    EmployeeRepository mockedEmployeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        when(mockedEmployeeRepository.findAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));

        //when
        List<Employee> allEmployees = employeeService.findAll();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeInMemoryRepository.create(any())).thenReturn(lucy);

        //when
        Employee createdEmployee = employeeService.create(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_create_given_a_employee_with_age_17() {
        //given
        Employee kitty = new Employee(1, "Kitty", 6, Gender.FEMALE, 8000.0);
        //when
        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(kitty));
        verify(mockedEmployeeInMemoryRepository, never()).create(any());
    }

    @Test
    void should_throw_EmployeeAgeNotValidException_when_create_given_a_employee_with_age_66() {
        //given
        Employee kitty = new Employee(1, "Kitty", 66, Gender.FEMALE, 8000.0);
        //when
        //then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.create(kitty));
        verify(mockedEmployeeInMemoryRepository, never()).create(any());
    }
//
//    @Test
//    void should_created_employee_active_when_create_employee() {
//        //given
//        EmployeeInMemoryRepository mockedEmployeeInMemoryRepository = mock(EmployeeInMemoryRepository.class);
//        EmployeeService employeeService = new EmployeeService(mockedEmployeeInMemoryRepository);
//        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
//        //when
//        employeeService.create(lucy);
//        /* then */
//        verify(mockedEmployeeInMemoryRepository).create(argThat(Employee::getActive));
//    }
//
//    @Test
//    void should_throw_EmployeeAgeSalaryNotMatchedException_when_save_given_a_employee_with_age_over_30_and_salary_below_20K() {
//        //given
//        EmployeeInMemoryRepository mockedEmployeeInMemoryRepository = mock(EmployeeInMemoryRepository.class);
//        Employee bob = new Employee(1, "Bob", 31, Gender.FEMALE, 8000.0);
//        EmployeeService employeeService = new EmployeeService(mockedEmployeeInMemoryRepository);
//        //when
//        //then
//        assertThrows(EmployeeAgeSalaryNotMatchedException.class, () -> employeeService.create(bob));
//        verify(mockedEmployeeInMemoryRepository, never()).create(any());
//    }
//
//    @Test
//    void should_throw_EmployeeInactiveException_when_update_inactive_employee() {
//        //given
//        EmployeeInMemoryRepository mockedEmployeeInMemoryRepository = mock(EmployeeInMemoryRepository.class);
//        Employee inactiveEmployee = new Employee(1, "Bob", 31, Gender.FEMALE, 8000.0);
//        inactiveEmployee.setActive(false);
//        when(mockedEmployeeInMemoryRepository.findById(1)).thenReturn(inactiveEmployee);
//        EmployeeService employeeService = new EmployeeService(mockedEmployeeInMemoryRepository);
//        //when
//        //then
//        assertThrows(EmployeeInactiveException.class, () -> employeeService.update(1, inactiveEmployee));
//        verify(mockedEmployeeInMemoryRepository, never()).create(any());
//    }
}


