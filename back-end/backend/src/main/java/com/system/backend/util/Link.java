package com.system.backend.util;

public class Link {
    public static final String CORS = "http://localhost:3000";
    public static final String API_ROOT = "/api/v1";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static class USER{
        public static final String MEMBER = "/user/member";

        public static class MEMBERCRUD{

            public static final String GET = "/singleuser/{account}";
            public static final String GETONEALLROLE = "/singleuser/allrole/{account}";
            public static final String PUT = "/setpass";
            public static final String DELETE = "/users";
            public static final String POST = "/users";
        }

        public static final String STAFF = "/user/staff";
        public static class STAFFCRUD{

            public static final String GET = "/singleuser/{user_id}";
            public static final String PUT = "/{user_id}";
            public static final String DELETE = "/{user_id}";
            //public static final String POST = "/users";
        }
        public static final String ADMIN = "/user/admin";
        public static class ADMINCRUD{

            public static final String GET = "/singleuser/{account}";
            public static final String GETALL = "/setpass";
            public static final String DELETE = "/users";
            public static final String POSTADMIN = "/addadmin";
        }
        public static final String PRODUCT = "/user/products";

        public static class PRODUCTCRUD{
            public static final String GETALLL = "/getAll";
            public static final String GET = "/one/{product_id}";
            public static final String GETONEFROMUSER = "/one/{account}-{product_id}";
            public static final String GETALLFROMUSER = "/{account}";
            public static final String PUT = "/{account}-{product_id}";
            public static final String DELETE = "/{account}-{product_id}";
            public static final String POST = "/{account}";
        }
        public static final String  POSTUP = "/user/posts";

        public static class POSTCRUD{
            public static final String GETALLL = "/getALL";
            public static final String GET = "/one/{post_id}";
            public static final String PUT = "/{account}-{product_id}";
            public static final String DELETE = "/{account}-{product_id}";
            public static final String POST = "/{account}";
        }


    }





}
