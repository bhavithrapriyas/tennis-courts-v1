package main.java.com.tenniscourts.guests;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.com.tenniscourts.schedules.Schedule;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
	List<Guest> findByGuest_NameOrderByName(String name);
	
}
