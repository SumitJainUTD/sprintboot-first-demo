package service;

import model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

        // DB repository mock
        private Map<Long, Employee> repository = Arrays.stream(
                        new Employee[]{
                                new Employee(1, "Alan","Turing"),
                                new Employee(2, "Sebastian","Bach"),
                                new Employee(3, "Pablo","Picasso"),
                        })
                .collect(Collectors.toConcurrentMap(Employee::getId, Function.identity()));

        // DB id sequence mock
        private AtomicLong sequence = new AtomicLong(3);

        public List<Employee> readAll() {
            return new ArrayList<>(repository.values());
        }

        public Employee read(Long id) {
            return repository.get(id);
        }

        public Employee create(Employee student) {
            long key = sequence.incrementAndGet();
            student.setId(key);
            repository.put(key, student);
            return student;
        }

        public Employee update(Long id, Employee student) {
            student.setId(id);
            Employee oldEmployee = repository.replace(id, student);
            return oldEmployee == null ? null : student;
        }

        public void delete(Long id) {
            repository.remove(id);
        }
}
