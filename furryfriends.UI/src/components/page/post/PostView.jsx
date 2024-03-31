import React from 'react'

import PostView from '../../layout/PostView'
import NavbarPost from '../../layout/NavbarPost'
import Footer from '../../layout/Footer'
import PostViewCSS from './Postview.module.css'
export default function PostUpForm() {


    return (
        <div className={PostViewCSS['postview']}>
              <NavbarPost/>
            <PostView />
            <Footer/>
        </div>

    );

}
