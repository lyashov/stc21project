package ru.innopolis.stc21.med.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.repository.MedicalHistoryRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MedicalHistoryService {
    @Autowired
    MedicalHistoryRepository repository;

    public List<MedicalHistoryEntity> getAllByUser(UsersEntity usersEntity)
    {
        List<MedicalHistoryEntity> historyList = repository.getAllByUserOrderByIdDesc(usersEntity);
        if(historyList.size() > 0) {
            return historyList;
        } else {
            return new ArrayList<MedicalHistoryEntity>();
        }
    }

   public MedicalHistoryEntity create(Date date, UsersEntity usersEntity) {
            MedicalHistoryEntity entityNew = new MedicalHistoryEntity();
            entityNew.setUser(usersEntity);
            entityNew.setDate_visit(date);
            entityNew.setNeiro_diagtose(" ");
            entityNew.setAccuracy(" ");
            return repository.save(entityNew);
    }

   /* public void save(UsersEntity usersEntity)
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
