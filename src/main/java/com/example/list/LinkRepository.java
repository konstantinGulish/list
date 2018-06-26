package com.example.list;

import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;

public interface LinkRepository extends CrudRepository <Link, Long> {

    Iterable <Link> findAllByResourseNameContaining(String resourceName);
    Iterable<Link> findAllByOrderByDayEnteredDesc();
}
