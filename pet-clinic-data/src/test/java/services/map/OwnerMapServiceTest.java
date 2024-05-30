package services.map;

import model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Singh";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setFirstName("Sidak");
        owner.setLastName(lastName);
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void save() {
        Owner owner = new Owner();
        owner.setLastName("Balaji");
        Owner savedOwner = ownerMapService.save(owner);
        assertEquals(owner.getId(), savedOwner.getId());
    }

    @Test
    void saveExistingId(){
        Long id =2L;
        Owner owner = new Owner();
        owner.setId(id);
        Owner savedOwner = ownerMapService.save(owner);
        assertEquals(id,savedOwner.getId());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());

    }

    @Test
    void findByLastName() {
        Owner found = ownerMapService.findByLastName("Singh");
        assertNotNull(found);
    }
}