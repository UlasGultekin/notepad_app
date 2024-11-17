package com.ulasgltkn.notpad_app.service;

import com.ulasgltkn.notpad_app.entity.Note;
import com.ulasgltkn.notpad_app.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public ResponseEntity<?> saveOrUpdate(Note note){
        Map<String, Object> hashMap = new HashMap<>();
        try{
            Optional note1 = noteRepository.findByPath(note.getPath());
            if (note1.isPresent()){
                Note note2= (Note) note1.get();
                note2.setNote(note.getNote());
                noteRepository.saveAndFlush(note2);
            }
            else {
                noteRepository.saveAndFlush(note);
            }
            hashMap.put("status",true);
            hashMap.put("message","Başarılı Şekilde Kayıt Yapıldı");
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        }catch(Exception ex){
            hashMap.put("status",false);
            hashMap.put("message","Kayıt İşlemi Başarısız Oldu");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }

    }
    public ResponseEntity<?> getPath(String path){
        Map<String, Object> hashMap = new HashMap<>();
        try{
            Optional note1 = noteRepository.findByPath(path);
            if (note1.isPresent()){
                Note note2= (Note) note1.get();
                hashMap.put("status",true);
                hashMap.put("Message","Aradığınız Not Getirildi");
                hashMap.put("response",note2);
                return new ResponseEntity<>(hashMap, HttpStatus.OK);

            }
            else {
                hashMap.put("status",false);
                hashMap.put("Message","Aradığınız Not Bulunamadı");

                return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            hashMap.put("status",false);
            hashMap.put("Message","Aradığınız Not İçin Hata Oluştu Lütfen Kontrol Ederek Tekrar Deneyin");

            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }
}
