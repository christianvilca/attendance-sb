package org.parish.attendancesb.repositories;

import org.parish.attendancesb.models.Attendance;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    public List<Attendance> findByCatequesisAndReceiverPerson(Catequesis catequesis, ReceiverPerson receiverPerson);

}
