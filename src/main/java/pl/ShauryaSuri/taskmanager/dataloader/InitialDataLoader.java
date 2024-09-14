package pl.ShauryaSuri.taskmanager.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import pl.ShauryaSuri.taskmanager.model.Role;
import pl.ShauryaSuri.taskmanager.model.Task;
import pl.ShauryaSuri.taskmanager.model.User;
import pl.ShauryaSuri.taskmanager.service.RoleService;
import pl.ShauryaSuri.taskmanager.service.TaskService;
import pl.ShauryaSuri.taskmanager.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("admin@gmail.com")
    private String defaultAdminMail;
    @Value("admin")
    private String defaultAdminName;
    @Value("112233")
    private String defaultAdminPassword;
    @Value("https://www.google.com")
    private String defaultAdminImage;

    @Autowired
    public InitialDataLoader(UserService userService, TaskService taskService, RoleService roleService) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //ROLES --------------------------------------------------------------------------------------------------------
        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);

        //USERS --------------------------------------------------------------------------------------------------------
        //1
        User admin = new User(
                defaultAdminMail,
                defaultAdminName,
                defaultAdminPassword,
                defaultAdminImage);
        userService.createUser(admin);
        userService.changeRoleToAdmin(admin);

        //2
        User manager = new User(
                "admin@taskco.com",
                "Manager",
                "112233",
                "images/admin.png");
        userService.createUser(manager);
        userService.changeRoleToAdmin(manager);

        //3
        userService.createUser(new User(
                "jason@taskco.com",
                "Jason",
                "112233",
                "images/jason.jpg"));

        //4
        userService.createUser(new User(
                "linda@taskco.com",
                "Linda",
                "112233",
                "images/linda.jpg"));

        //5
        userService.createUser(new User(
                "mike@taskco.com",
                "Mike",
                "112233",
                "images/mike.jpg"));

        //6
        userService.createUser(new User(
                "sarah@taskco.com",
                "Sarah",
                "112233",
                "images/sarah.jpg"));

        //7
        userService.createUser(new User(
                "john@taskco.com",
                "John",
                "112233",
                "images/john.jpg"));

        userService.findAll().stream()
                .map(u -> "saved user: " + u.getName())
                .forEach(logger::info);


        //TASKS --------------------------------------------------------------------------------------------------------
        //tasks from Web Design Checklist
        //https://www.beewits.com/the-ultimate-web-design-checklist-things-to-do-when-launching-a-website/

        LocalDate today = LocalDate.now();

        //1
        taskService.createTask(new Task(
                "Collect briefing document",
                "Setup first meeting with client. Collect basic data about the client. Define and collect the briefing document from the client.",
                today.minusDays(40),
                true,
                userService.getUserByEmail("jason@taskco.com").getName(),
                userService.getUserByEmail("jason@taskco.com")
        ));

        //2
        taskService.createTask(new Task(
                "Define project questionnaire",
                "Define and send project questionnaire to the client and wait for the client’s response. Iterate on doubts you have until agreement is reached. Finalize project questionnaire from the client.",
                today.minusDays(30),
                true,
                userService.getUserByEmail("linda@taskco.com").getName(),
                userService.getUserByEmail("linda@taskco.com")
        ));

        //3
        taskService.createTask(new Task(
                "Research client’s company and industry",
                "Research client’s company to understand their brand, communication style, demographics, and target audience. Research the client’s industry to find communication strategies, strengths, weaknesses, trends, and other industry specifics.",
                today.minusDays(20),
                true,
                userService.getUserByEmail("mike@taskco.com").getName(),
                userService.getUserByEmail("mike@taskco.com")
        ));

        //4
        taskService.createTask(new Task(
                "Get quotation for project",
                "Get quotations for the development effort, design work, copy/content, and estimate the effort for photography and video production.",
                today.minusDays(10),
                true,
                userService.getUserByEmail("sarah@taskco.com").getName(),
                userService.getUserByEmail("sarah@taskco.com")
        ));

        //5
        taskService.createTask(new Task(
                "Get quotation for hosting and domain",
                "Get a quotation for hosting and domain services, especially for specialized hosting such as VPS or cloud hosting.",
                today.minusDays(5),
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("sarah@taskco.com")
        ));

        //6
        taskService.createTask(new Task(
                "Check the quality of each content element",
                "Quality assure all outsourced or bought content. Ask for changes where necessary and populate the website with agreed content items.",
                today.minusDays(2),
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("jason@taskco.com")
        ));

        //7
        taskService.createTask(new Task(
                "Define a Contact Us page and social media details",
                "Create a Contact Us page with client details, map, and links to relevant social media. Ensure a website footer link to the client’s homepage is agreed upon.",
                today.minusDays(1),
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("jason@taskco.com")
        ));

        //8
        taskService.createTask(new Task(
                "Check all web copywriting",
                "Proofread all web copywriting and run it through grammar checkers like Reverso or Spellcheckplus. Ensure placeholder content (like lorem ipsum) is replaced.",
                today,
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("linda@taskco.com")
        ));

        //9
        taskService.createTask(new Task(
                "Check all images and videos",
                "Ensure images are correctly placed, formatted, and optimized for all devices. Confirm that videos and audio files are functioning properly.",
                today.plusDays(1),
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("linda@taskco.com")
        ));

        //10
        taskService.createTask(new Task(
                "Check all linked content",
                "Verify that all linked content, including case studies and ebooks, is properly linked. Test all internal links and confirm that the company logo redirects to the homepage.",
                today.plusDays(2),
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("sarah@taskco.com")
        ));

        //11
        taskService.createTask(new Task(
                "Check Contact Us and other forms",
                "Ensure all forms are submitting data correctly, either to email or a monitored database. Check auto-responders and proof emails.",
                today.plusDays(3),
                false,
                userService.getUserByEmail("sarah@taskco.com").getName(),
                userService.getUserByEmail("sarah@taskco.com")
        ));

        //12
        taskService.createTask(new Task(
                "Check all external links",
                "Verify that external links are functional and open in a new tab. Ensure social media share icons are working, with appropriate metadata for sharing.",
                today.plusDays(4),
                false,
                userService.getUserByEmail("linda@taskco.com").getName(),
                userService.getUserByEmail("linda@taskco.com")
        ));

        //13
        taskService.createTask(new Task(
                "Check the 404 page and redirects",
                "Test the 404 page and ensure only one version of the domain (www or non-www) is active to prevent penalties for duplicate content.",
                today.plusDays(5),
                false,
                userService.getUserByEmail("mike@taskco.com").getName(),
                userService.getUserByEmail("mike@taskco.com")
        ));

        //14
        taskService.createTask(new Task(
                "Check if the website is mobile-friendly",
                "Ensure the website uses a viewport meta tag and passes Google's mobile-friendly test. Check for proper input fields for mobile devices.",
                today.plusDays(6),
                false,
                userService.getUserByEmail("admin@taskco.com").getName(),
                userService.getUserByEmail("admin@taskco.com")
        ));

        //15
        taskService.createTask(new Task(
                "Test website on emulators and real devices",
                "Test the website on emulators (iPadpeek, Screenfly) and real devices to check cross-device functionality.",
                today.plusDays(8),
                false,
                userService.getUserByEmail("admin@taskco.com").getName()
        ));

        //16
        taskService.createTask(new Task(
                "Check page titles, meta descriptions, and keywords",
                "Ensure all pages have unique titles and meta descriptions within character limits, and avoid keyword stuffing.",
                today.plusDays(10),
                false,
                userService.getUserByEmail("admin@taskco.com").getName()
        ));

        //17
        taskService.createTask(new Task(
                "Check page URLs",
                "Verify that all page URLs reflect the site’s structure, and set up 301 redirects for old URLs.",
                today.plusDays(12),
                false,
                userService.getUserByEmail("admin@taskco.com").getName()
        ));

        //18
        taskService.createTask(new Task(
                "Minify and optimize files",
                "Minify JavaScript and CSS, optimize image sizes, and enable gzip compression on the server.",
                today.plusDays(14),
                false,
                userService.getUserByEmail("admin@taskco.com").getName()
        ));

        //19
        taskService.createTask(new Task(
                "Register social media properties",
                "Set up cover and profile images for social media accounts and ensure links back to the website are in place.",
                today.plusDays(16),
                false,
                userService.getUserByEmail("admin@taskco.com").getName()
        ));

        //20
        taskService.createTask(new Task(
                "Send the finished site to the client",
                "Submit the final site to the client, handle feedback, and wait for client sign-off.",
                today.plusDays(18),
                false,
                userService.getUserByEmail("admin@taskco.com").getName()
        ));


        taskService.findAll().stream().map(t -> "saved task: '" + t.getName()
                + "' for owner: " + getOwnerNameOrNoOwner(t)).forEach(logger::info);
    }

    private String getOwnerNameOrNoOwner(Task task) {
        return task.getOwner() == null ? "no owner" : task.getOwner().getName();
    }
}
