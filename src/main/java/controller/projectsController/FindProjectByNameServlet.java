package controller.projectsController;
import configur.HikariProvider;
import configur.PropertiesUtil;
import configur.DataBaseManagerConnector;
import dl.ProjectsRepository;
import model.converter.ProjectsConverter;
import model.dto.ProjectsDto;
import service.ProjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns="/findProjectByName")
public class FindProjectByNameServlet extends HttpServlet {

    ProjectsService service;

    @Override
    public void init() {
        PropertiesUtil props = new PropertiesUtil(getServletContext());
        DataBaseManagerConnector connector = new HikariProvider(props.getHostname(),
                props.getPort(), props.getSchema(), props.getUser(),
                props.getPassword(), props.getJdbcDriver());
        service = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(connector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("name");
        ProjectsDto project = service.findByName(projectId);
        req.setAttribute("project", project);
        req.getRequestDispatcher("/JSP/findProjectByNameForm.jsp").forward(req, resp);
    }
}
