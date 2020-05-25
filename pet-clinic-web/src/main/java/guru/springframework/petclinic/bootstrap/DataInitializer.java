package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.model.Pet;
import guru.springframework.petclinic.model.PetType;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.services.OwnerService;
import guru.springframework.petclinic.services.PetTypeService;
import guru.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// CommandLineRunner is a Spring Boot specific class
@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Larry");
        owner1.setLastName("Barold");
        owner1.setAddress("123 Mess Off Lane");
        owner1.setCity("New Bedford");
        owner1.setTelephone("555-444-1234");
        ownerService.save(owner1);

        Pet larryPet = new Pet();
        larryPet.setPetType(savedDogPetType);
        larryPet.setOwner(owner1);
        larryPet.setBirthDate(LocalDate.now());
        larryPet.setName("Bubsie");
        owner1.getPets().add(larryPet);



        Owner owner2 = new Owner();
        owner2.setFirstName("Barry");
        owner2.setLastName("Larold");
        owner2.setAddress("409 Huttleston Ave");
        owner2.setCity("Fairhaven");
        owner2.setTelephone("555-123-3333");
        ownerService.save(owner2);

        Pet barryPet = new Pet();
        barryPet.setPetType(savedCatPetType);
        barryPet.setOwner(owner2);
        barryPet.setBirthDate(LocalDate.now());
        barryPet.setName("Kitty");
        owner1.getPets().add(barryPet);

        System.out.println("Loaded owners.......");

        Vet vet1 = new Vet();
        vet1.setFirstName("Scary");
        vet1.setLastName("Terry");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Scary");
        vet2.setLastName("Brandon");

        System.out.println("Loaded Vets............");

        vetService.save(vet2);



    }
}
