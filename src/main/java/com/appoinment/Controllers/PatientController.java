package com.appoinment.Controllers;

import com.appoinment.dto.AppointmentDTO;
import com.appoinment.dto.PatientDTO;
import com.appoinment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        List<PatientDTO> patientList = this.patientService.getAll();
        return ResponseEntity.ok(patientList);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDTO) {
        PatientDTO result = this.patientService.save(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /*
        Recibe el id del paciente para obtener su turno asignado
     */
    @GetMapping("/appointment/{id}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(this.patientService.getAppointment(id));
    }
}
