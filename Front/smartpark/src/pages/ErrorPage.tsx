import React from 'react';
import { Link } from 'react-router-dom';
import image from '../assets/imgs/Coupe-du-monde-2030.png';

function ErrorPage() {
    return (
        <section className="bg-white dark:bg-gray-900">
            <div className="container min-h-screen px-6 py-12 mx-auto lg:flex lg:items-center lg:gap-12">
                <div className="w-full lg:w-1/2">
                    <p className="text-sm font-medium text-red-500 dark:text-blue-400">404 error</p>
                    <h1 className="mt-3 text-2xl font-semibold text-gray-800 dark:text-white md:text-3xl">Page not found</h1>
                    <p className="mt-4 text-gray-500 dark:text-gray-400">Sorry, the page you are looking for doesn't exist. Here are some helpful links:</p>

                    <div className="flex items-center mt-6 gap-x-3">
                        <Link to="/" className="flex items-center justify-center w-1/2 px-5 py-2 text-sm text-white bg-green-500  transition-colors duration-200  border rounded-lg gap-x-2 sm:w-auto  hover:bg-green-600  shrink-0   dark:hover:bg-green-500 dark:bg-green-600">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5 rtl:rotate-180">
                                <path strokeLinecap="round" strokeLinejoin="round" d="M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18" />
                            </svg>
                            <span>Go back</span>
                        </Link>

                        <Link to="/" className="w-1/2 px-5 py-2 text-sm tracking-wide text-white transition-colors duration-200 bg-red-500 rounded-lg shrink-0 sm:w-auto hover:bg-red-600 dark:hover:bg-red-500 dark:bg-red-600">
                            Take me home
                        </Link>
                    </div>
                </div>

                <div className="flex items-center justify-center w-full mt-6 lg:mt-0 lg:w-1/2 animate-pulse">
                    <img src={image} alt="404" className="w-full h-full" />
                </div>
            </div>
        </section>
    );
};

export default ErrorPage;
