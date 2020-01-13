package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Bucket;
import internetShop.service.BucketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateBucketController extends HttpServlet {
    private static final Long USER_ID=1L;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket=bucketService.getByUserId(USER_ID);
        bucketService.create(bucket);

        req.setAttribute("bucket",bucket);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req,resp);
    }
}
