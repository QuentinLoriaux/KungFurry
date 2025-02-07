// package com.ensta.myfilmlist;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.Optional;
// import java.util.List;

// import javax.persistence.EntityManager;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.ensta.myfilmlist.model.Utilisateur;
// import com.ensta.myfilmlist.dao.impl.JpaUtilisateurDAO;

// class JpaUtilisateurDAOTest {

//    @Mock
//    private EntityManager entityManager;

//    @InjectMocks
//    private JpaUtilisateurDAO utilisateurDAO;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

//    @Test
//    void testFindAll() {
//        List<Utilisateur> mockUsers = List.of(new Utilisateur(1L, "user1"), new Utilisateur(2L, "user2"));
//        when(entityManager.createQuery("SELECT f FROM Utilisateur f", Utilisateur.class).getResultList()).thenReturn(mockUsers);

//        List<Utilisateur> result = utilisateurDAO.findAll();

//        assertEquals(2, result.size());
//        assertEquals("user1", result.get(0).getUsername());
//        verify(entityManager, times(1)).createQuery("SELECT f FROM Utilisateur f", Utilisateur.class);
//    }

//    @Test
//    void testSave() {
//        Utilisateur user = new Utilisateur();
//        user.setUsername("newUser");

//        utilisateurDAO.save(user);

//        verify(entityManager, times(1)).persist(user);
//    }

//    @Test
//    void testFindById() {
//        Utilisateur user = new Utilisateur(1L, "user1");
//        when(entityManager.find(Utilisateur.class, 1L)).thenReturn(user);

//        Optional<Utilisateur> result = utilisateurDAO.findById(1L);

//        assertTrue(result.isPresent());
//        assertEquals("user1", result.get().getUsername());
//        verify(entityManager, times(1)).find(Utilisateur.class, 1L);
//    }

//    @Test
//    void testDelete_UserExists() {
//        Utilisateur user = new Utilisateur(1L, "user1");
//        when(entityManager.find(Utilisateur.class, user.getId())).thenReturn(user);
//        when(entityManager.contains(user)).thenReturn(true);

//        utilisateurDAO.delete(user);

//        verify(entityManager, times(1)).remove(user);
//    }

//    @Test
//    void testDelete_UserMerged() {
//        Utilisateur user = new Utilisateur(1L, "user1");
//        Utilisateur mergedUser = new Utilisateur(1L, "user1Merged");

//        when(entityManager.find(Utilisateur.class, user.getId())).thenReturn(user);
//        when(entityManager.contains(user)).thenReturn(false);
//        when(entityManager.merge(user)).thenReturn(mergedUser);

//        utilisateurDAO.delete(user);

//        verify(entityManager, times(1)).merge(user);
//        verify(entityManager, times(1)).remove(mergedUser);
//    }

//    @Test
//    void testUpdate() {
//        Utilisateur user = new Utilisateur(1L, "updatedUser");

//        when(entityManager.merge(user)).thenReturn(user);

//        Utilisateur result = utilisateurDAO.update(user);

//        assertEquals("updatedUser", result.getUsername());
//        verify(entityManager, times(1)).merge(user);
//    }
// }
