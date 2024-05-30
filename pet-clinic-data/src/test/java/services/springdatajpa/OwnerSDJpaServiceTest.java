package services.springdatajpa;

import model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.OwnerRepository;
import repositories.PetRepository;
import repositories.PetTypeRepository;

import javax.print.DocFlavor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    private final static String lastName = "Singh";

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setLastName(lastName);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner returned = ownerRepository.findByLastName(lastName);
        assertEquals(lastName,returned.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        owner1.setId(1L);
        owner2.setLastName(lastName);
        ownerSet.add(owner1);
        ownerSet.add(owner2);

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        //Set<Owner> owners = (Set<Owner>) ownerRepository.findAll();
        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner1 = ownerSDJpaService.findById(2L);

        assertNull(owner1);
    }

    @Test
    void save() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        Owner returned = ownerSDJpaService.save(owner1);
        assertNull(returned);

        when(ownerSDJpaService.save(any())).thenReturn(owner1);

        returned = ownerSDJpaService.save(owner1);
        assertNotNull(returned);
        verify(ownerRepository,times(2)).save(any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}