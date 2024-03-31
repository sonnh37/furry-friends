package com.system.backend.util;

public class Link {
    public static final String CORS = "http://localhost:3000";
    public static final String API_ROOT = "/api/v1";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static class USER{
        public static final String MEMBER = "/user/member";
        public static final String STATISTIC = "/user/statistic";
        public static class STATISTICCRUD{

            public static final String STATISTICPRODUCT = "/statisticProduct";
            public static final String STATISTICPOST = "/statisticPost";
            public static final String STATISTICPOSTLIKE = "/statisticPostLike";
            public static final String STATISTICPOSTCOMMENT = "/statisticPostComment";
            public static final String STATISTICUSER = "/statisticUser";
            public static final String STATISTICPET = "/statisticPet";
        }
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
            public static final String DELETEPOST = "/posts/{post_id}";
            public static final String DELETEPRODUCT = "/products/{product_id}";
            public static final String DELETEPET = "/pets/{pet_id}";
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
            public static final String GETBYSEARCH_DESC = "/getSearch_desc";
            public static final String GETALLL_DESC = "/getAll_desc";
            public static final String GETBYSEARCH_ASC = "/getSearch_asc";
            public static final String GETALLL_ASC = "/getAll_asc";
            public static final String GET = "/one/{product_id}";
            public static final String GETALLFROMUSER = "/{account}";
            public static final String PUT = "/{account}-{product_id}";
            public static final String DELETE = "/{account}-{product_id}";
            public static final String POST = "/{account}";
        }
        public static final String  POSTUP = "/user/posts";

        public static class POSTCRUD{
            public static final String GETBYSEARCH_DESC = "/getSearch_desc";
            public static final String GETALLL_DESC = "/getALL_desc";

            public static final String GETBYSEARCH_ASC = "/getSearch_asc";
            public static final String GETALLL_ASC = "/getALL_asc";
            public static final String GETALLLBYACCOUNT = "/getALL/{account}";
            public static final String GET = "/one/{post_id}";
            public static final String PUT = "/{account}-{post_id}";
            public static final String DELETE = "/{account}-{post_id}";
            public static final String POST = "/{account}";
        }
        public static final String  POSTLIKE = "/user/likes";
        public static class POST_LIKE_CRUD{
            public static final String GETALLL = "/getAll";
            public static final String GET = "/one/{post_id}";
            public static final String PUT = "/{account}-{post_id}";
            public static final String DELETE = "/{account}-{post_id}";
            public static final String POST = "/{account}-{post_id}";
        }
        public static final String  POSTCOMMENT = "/user/comments";
        public static class POST_CMT_CRUD{
            public static final String GETALLL = "/getALL/{post_id}";
            public static final String GET = "/one/{post_id}";
            public static final String PUT = "/{account}-{post_id}";
            public static final String DELETE = "/{post_id}-{comment_id}";
            public static final String POST = "/{account}-{post_id}";
        }

        public static final String  PET = "/user/pets";
        public static class PET_CRUD{
            public static final String GETBYSEARCH = "/getSearch";
            public static final String GETALLL_DESC = "/getAll_desc";
            public static final String GETTYPE_DESC = "/type_desc/{type}";
            public static final String GETALLL_ASC = "/getAll_asc";
            public static final String GETTYPE_ASC = "/type_asc/{type}";
            public static final String GET = "/one/{pet_id}";

            public static final String GETALLFROMUSER = "/{account}";
            public static final String PUT = "/{account}-{pet_id}";

            public static final String DELETE = "/{account}-{pet_id}";
            public static final String POST = "/{account}";

        }



    }





}
