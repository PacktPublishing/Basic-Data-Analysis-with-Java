/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.fileformats;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


/**
 *
 * @author Erik Costlow
 */
public class WebDriverTest {

    @Test
    public void testPakt() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://www.packtpub.com/");
        WebElement element = driver.findElement(By.id("five-dollar-search-keys"));
        assertNotNull("Unable to locate search box", element);
        element.sendKeys("analysis");
        element.submit();
        
        final List<WebElement> books = driver.findElements(By.className("book-block-title"));
        assertFalse("No books found", books.isEmpty());
        System.out.println();
        System.out.println(String.format("Printing %d books", books.size()));
        books.stream()
                .map(bookDiv -> bookDiv.getText())
                .sorted()
                .forEach(System.out::println);
    }
}
