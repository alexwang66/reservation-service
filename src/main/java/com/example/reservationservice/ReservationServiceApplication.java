package com.example.reservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}

@Component
class SampleDataCLR implements CommandLineRunner{

	private final ReservationRepository reservationRepository;

	@Override
	public void run(String... strings) throws Exception {
		Stream.of("Josh","Alex","LYQ").forEach(name ->reservationRepository.save(new Reservation(name)));
		reservationRepository.findAll().forEach(System.out::println);
	}

	@Autowired
	public SampleDataCLR(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

@Entity
class Reservation{

	public Reservation() {

	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	@Id
	@GeneratedValue
	private Long id;

	private String reservationName;

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}
}
