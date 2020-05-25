package guru.springframework.petclinic.services;

import guru.springframework.petclinic.model.BaseEntity;
import guru.springframework.petclinic.model.PetType;

public interface PetTypeService<P extends BaseEntity, L extends Number> extends CrudService<PetType, Long> {
}
