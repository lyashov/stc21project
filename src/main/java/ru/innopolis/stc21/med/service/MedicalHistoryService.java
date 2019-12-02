package ru.innopolis.stc21.med.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc21.med.configs.Role;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.repository.MedicalHistoryRepository;
import ru.innopolis.stc21.med.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {
    @Autowired
    MedicalHistoryRepository repository;

    public List<MedicalHistoryEntity> getAllByUser(UsersEntity usersEntity)
    {
        List<MedicalHistoryEntity> historyList = repository.getAllByUser(usersEntity);
        if(historyList.size() > 0) {
            return historyList;
        } else {
            return new ArrayList<MedicalHistoryEntity>();
        }
    }

   /* public UsersEntity create(String username, String password,
                              String first_name, String second_name, String last_name,
                              String email, String snils) {
            UsersEntity entityNew = new UsersEntity();
            //`entityNew.setId(4L);
            entityNew.setActive(true);
            entityNew.setUsername(username);
            entityNew.setPassword(password);
            entityNew.setAuthorities(ImmutableList.of(Role.PACIENT));
            entityNew.setAccountNonExpired(true);
            entityNew.setAccountNonLocked(true);
            entityNew.setEnabled(true);
            entityNew.setCredentialsNonExpired(true);
            entityNew.setFirst_name(first_name);
            entityNew.setSecond_name(second_name);
            entityNew.setLast_name(last_name);
            entityNew.setEmail(email);
            entityNew.setSnils(snils);
            return repository.save(entityNew);
    }

    public void save(UsersEntity usersEntity)
    {
        repository.save(usersEntity);
    }

    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UsersEntity> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }*/
}
