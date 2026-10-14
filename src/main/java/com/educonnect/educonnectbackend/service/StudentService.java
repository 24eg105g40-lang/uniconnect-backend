package com.educonnect.educonnectbackend.service;
import com.educonnect.educonnectbackend.exception.ResourceNotFoundException;
import com.educonnect.educonnectbackend.repository.*;
import com.educonnect.educonnectbackend.mapper.StudentMapper;
import com.educonnect.educonnectbackend.dto.StudentDTO;
import com.educonnect.educonnectbackend.entity.Address;
import com.educonnect.educonnectbackend.entity.Course;
import com.educonnect.educonnectbackend.entity.Department;
import com.educonnect.educonnectbackend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
       @Autowired
       private StudentRepository studentRepository;
       @Autowired
       private CourseRepository courseRepository;
       @Autowired
       private DepartmentRepository departmentRepository;
       @Autowired
       private AddressRepository addressRepository;

       public Page<StudentDTO> getAllStudents(int page, int size, String sortBy, String direction) {
              Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
              Pageable pageable = PageRequest.of(page, size, sort);
              return studentRepository.findAll(pageable).map(StudentMapper:: toDTO);
       }
       public StudentDTO getStudentByID(Long id){
              Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student does not exist in database"));
              return StudentMapper.toDTO(student);
       }
       public StudentDTO createStudent(StudentDTO studentDTO){
              Student student = new Student();
              student.setName(studentDTO.getName());
              student.setEmail(studentDTO.getEmail());
              applyRelation(student, studentDTO);
              return StudentMapper.toDTO(studentRepository.save(student));
       }
       public StudentDTO updateStudent(Long id, StudentDTO studentDTO){
              Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student does not exist in database"));
              student.setName(studentDTO.getName());
              student.setEmail(studentDTO.getEmail());
              applyRelation(student, studentDTO);
              return StudentMapper.toDTO(studentRepository.save(student));
       }
       public void deleteStudent(Long id){
              if(!studentRepository.findById(id).isPresent()){
                     throw new ResourceNotFoundException("Student does not exist in database");
              }
              studentRepository.deleteById(id);
       }
       public void applyRelation(Student student, StudentDTO studentDTO){
              if(studentDTO.getDepartment() != null){
                     Department dept = departmentRepository.findById(studentDTO.getDepartment().getId())
                             .orElseThrow(()->new ResourceNotFoundException("Department does not exist in database"));
              student.setDepartment(dept);
              }
              if(studentDTO.getAddress() != null ){
                     Address addr  = new Address(null,
                     studentDTO.getAddress().getCity(),
                     studentDTO.getAddress().getState(),
                     studentDTO.getAddress().getPincode());
              student.setAddress(addressRepository.save(addr));
              }
              if(studentDTO.getCourses() != null){
                    List<Course> courses= studentDTO.getCourses().stream()
                            .map(c -> courseRepository.findById(c.getId())
                                    .orElseThrow(()->new ResourceNotFoundException("Course does not exist in database")))
                                    .collect(Collectors.toList());
              student.setCourses(courses);

              }
       }

}
