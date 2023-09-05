package com.library.controller;

import com.library.bean.ReaderCard;
import com.library.service.BookService;
import com.library.service.LendService;
import com.library.service.ReserveService;
import com.library.service.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LendController {
    @Autowired
    private LendService lendService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private ReaderCardService readerCardService;

    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        if(bookService.bookisLend(bookId)){
            redirectAttributes.addFlashAttribute("error", "图书被借阅，不能删除！");
        }
        else {
            if (bookService.deleteBook(bookId)) {
                redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            }
        }
        return "redirect:/admin_books.html";
    }

    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin_lend_list");
        modelAndView.addObject("list", lendService.lendList());
        return modelAndView;
    }

    @RequestMapping("/mylend.html")
    public ModelAndView myLend(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView = new ModelAndView("reader_lend_list");
        modelAndView.addObject("list", lendService.myLendList(readerCard.getReaderId()));
        return modelAndView;
    }

    @RequestMapping("/deletelend.html")
    public String deleteLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long serNum = Long.parseLong(request.getParameter("serNum"));
        if (lendService.deleteLend(serNum) > 0) {
            redirectAttributes.addFlashAttribute("succ", "记录删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "记录删除失败！");
        }
        return "redirect:/lendlist.html";
    }

    @RequestMapping("/lendbook.html")
    public String bookLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        int borrow_times = readerCardService.getBorrowTime(readerId);
        if(borrow_times >5){
            redirectAttributes.addFlashAttribute("error", "图书借阅已达上限！");
        }
        else{
            if (lendService.lendBook(bookId, readerId) ) {
                reserveService.takeBook(bookId, readerId);
                redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "图书借阅失败！");
            }
        }
        return "redirect:/reader_books.html";
    }

    @RequestMapping("/returnbook.html")
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        int status;
        if(reserveService.bookReserveList(bookId).isEmpty())
            status = 0;
        else status = 2;
        if (lendService.returnBook(bookId, readerId,status) ) {
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
        }
        return "redirect:/reader_books.html";
    }
}
