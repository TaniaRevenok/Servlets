package controller.companiesControllet;
import configur.HikariProvider;
import configur.PropertiesUtil;
import configur.DataBaseManagerConnector;
import dl.CompaniesRepository;
import model.converter.CompaniesConverter;
import model.dto.CompaniesDto;
import service.CompaniesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/findCompanyById")
public class FindCompanyByIdServlet extends HttpServlet {

    CompaniesService service;

    @Override
    public void init() throws ServletException {
        PropertiesUtil props = new PropertiesUtil(getServletContext());
        DataBaseManagerConnector connector = new HikariProvider(props.getHostname(),
                props.getPort(), props.getSchema(), props.getUser(),
                props.getPassword(), props.getJdbcDriver());
        service = new CompaniesService(new CompaniesConverter(), new CompaniesRepository(connector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyId = req.getParameter("id");
        CompaniesDto company = service.findById(Integer.parseInt(companyId));
        req.setAttribute("c", company);
        req.getRequestDispatcher("/JSP/findCompanyById.jsp").forward(req, resp);
    }

}
