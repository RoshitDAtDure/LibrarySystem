package com.example.Library_System.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//name of data model is tutorial with 3 fields : id,type,status
@Data                // GETTER AND SETTER, CONSTRUCTORS, BUILDER
@AllArgsConstructor  // VERY IMPORTANT. HELPS WITH ALL CONSTRUCTORS WHICH CAN BE USED IN CONTROLLER.
@NoArgsConstructor

public class Genre {
	
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private boolean published;

}
