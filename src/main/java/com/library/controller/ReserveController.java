package com.library.controller;

import com.library.bean.ReaderCard;
import com.library.service.LendService;
import com.library.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
@Controller
public class ReserveController {
    @Autowired
    private ReserveService reserveService;
    @RequestMapping("/reservebook.html")
    public String bookReserve(HttpServletRequest request, RedirectAttributes redirectAttributes){;
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        if(reserveService.reserveBook(bookId, readerId)){
            redirectAttributes.addFlashAttribute("succ", "图书预约成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书预约失败！");
        }
        return "redirect:/reader_books.html";
    }
}
