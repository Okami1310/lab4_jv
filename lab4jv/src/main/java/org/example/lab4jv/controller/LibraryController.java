package org.example.lab4jv.controller;

import org.example.lab4jv.repo.LibraryRepository;
import org.example.lab4jv.data.Library;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class LibraryController {

    private LibraryRepository libraryRepository;

    @GetMapping("/returnS")
    public String homeS(){
        return"index";
    }


    @GetMapping("/library")
    public String showLibrary(Model model){
        List<Library> library = libraryRepository.findAll();
        model.addAttribute("library", library);
        return "library";
    }

    @PostMapping("/addlibrary")
    public String addLibrary(@RequestParam String name ,@RequestParam String author ,@RequestParam int year){
        Library library = new Library();
        library.setName(name);
        library.setAuthor(author);
        library.setYear(year);
        libraryRepository.save(library);
        return "redirect:/library";
    }

    @GetMapping("/delete_library")
    public String deleteLibrary(@RequestParam int id) {
        libraryRepository.deleteById(id);
        return "redirect:/library";
    }

    @GetMapping("/library_edit")
    public String libraryEdit(@RequestParam int id, Model model){
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if(optionalLibrary.isEmpty()){
            return "redirect:/library";
        }
        model.addAttribute("library",optionalLibrary.get());
        return "library_edit";
    }

    @PostMapping("/update_library")
    public String schedulUpdate(@RequestParam int id,@RequestParam String name ,@RequestParam String author ,@RequestParam int year) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        optionalLibrary.ifPresent(s -> {
            s.setName(name);
            s.setAuthor(author);
            s.setYear(year);
            libraryRepository.save(s);
        });
        return "redirect:/library";
    }

}
