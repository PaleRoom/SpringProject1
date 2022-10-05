package ru.nicolas.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nicolas.dao.BookDAO;
import ru.nicolas.dao.PersonDAO;
import ru.nicolas.models.Book;
import ru.nicolas.models.Person;
import ru.nicolas.util.BookValidator;
import ru.nicolas.util.PersonValidator;


@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;



    private final BookValidator bookValidator;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
             this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model, Model model2) {
        model.addAttribute("book", bookDAO.index());
        model.addAttribute("person", personDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, Model model2, @ModelAttribute("emptyPerson") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("customer",personDAO.showCustomer(id));
        return "book/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        // model.addAttribute("person", new Person());
        return "book/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "book/new";
        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "book/edit";
        bookDAO.update(id,book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/makeOrder")
    public String makeOrder(@ModelAttribute("book")  @PathVariable("id") int id, Person person) {
        bookDAO.makeOrder(id, person);

        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/closeOrder")
    public String closeOrder(@ModelAttribute("book")  @PathVariable("id") int id) {
        bookDAO.closeOrder(id);

        return "redirect:/book/{id}";
    }

}