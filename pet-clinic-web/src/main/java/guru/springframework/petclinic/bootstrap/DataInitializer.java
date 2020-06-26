package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.*;
import guru.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// CommandLineRunner is a Spring Boot specific class
@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        /*
        You don't need the data to load every time you
        run the program.  This says if the data already
        exists then don't load it again
         */
        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        /*
        The next line of code ensures you are getting the
        saved version which has an Id value from the map
        service.  "Specialty radiology" has no Id and
        can't be persisted.  This is how the map service
        is acting like Spring Data JPA.

         */
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

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

        Visit catVisit = new Visit();
        catVisit.setPet(barryPet);
        catVisit.setLocalDate(LocalDate.now());
        catVisit.setDescription("Climbs curtains");

        System.out.println("Loaded owners.......");

        Vet vet1 = new Vet();
        vet1.setFirstName("Scary");
        vet1.setLastName("Terry");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Scary");
        vet2.setLastName("Brandon");
        vet2.getSpecialties().add(savedDentistry);

        System.out.println("Loaded Vets............");

        vetService.save(vet2);
    }
}
