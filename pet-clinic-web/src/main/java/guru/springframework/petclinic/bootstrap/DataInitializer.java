package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.model.PetType;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.services.OwnerService;
import guru.springframework.petclinic.services.PetTypeService;
import guru.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        PetType savedPetDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Larry");
        owner1.setLastName("Barold");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Barry");
        owner2.setLastName("Larold");

        ownerService.save(owner2);

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
