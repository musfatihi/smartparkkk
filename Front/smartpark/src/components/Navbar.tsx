import React from 'react';
import logo from '../assets/imgs/logo.png';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <header className="relative">
            <div className="container px-6 py-6 mx-auto lg:flex lg:items-center lg:justify-between">
                <div className="flex items-center justify-between ">
                    <a className="flex items-center -mx-1" href="#">
                        <img className="w-8 h-8 mx-1 sm:h-10 sm:w-10" src={logo} alt="logo" />


                        <div className="mx-1 text-gray-700">
                            <h3 className="uppercase tracking-[0.15em] font-medium ">SmartParking</h3>
                            <p className="text-xs italic "></p>
                        </div>
                    </a>

                    <button className="text-gray-600 lg:hidden ">
                        <svg xmlns="http://www.w3.org/2000/svg" className="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M4 6h16M4 12h16m-7 6h7" />
                        </svg>
                    </button>
                </div>

                <div className=" absolute lg:static transition-all duration-300 w-full py-12 lg:py-0 left-1/2 lg:opacity-100 lg:translate-x-0 lg:bg-transparent lg:w-auto -translate-x-1/2 top-20 sm:top-24 bg-[#475F45] ">
                    <nav className="flex flex-col items-center space-y-8 lg:flex-row lg:space-y-0 lg:-mx-4">
                        <Link to="/">
                            <a className="font-medium text-white lg:text-[#475F45] lg:hover:text-gray-400 lg:mx-4">
                                Home
                            </a>
                        </Link>
                        <Link to="/parkings">
                            <a className="font-medium text-white lg:text-[#475F45] lg:hover:text-gray-400 lg:mx-4">
                                Parkings
                            </a>
                        </Link>
                        <Link to="/floors">
                            <a className="font-medium text-white lg:text-[#475F45] lg:hover:text-gray-400 lg:mx-4">
                                Floors
                            </a>
                        </Link>
                        <Link to="/parkingspaces">
                            <a className="font-medium text-white lg:text-[#475F45] lg:hover:text-gray-400 lg:mx-4">
                                Parking Spaces
                            </a>
                        </Link>
                        <Link to="/test">
                            <a className="px-8 py-2.5 text-white lg:text-[#475F45] lg:hover:bg-[#475F45] lg:hover:text-white duration-300 transition-colors font-medium lg:mx-4 border-2 lg:border-[#475F45] border-white" href="#">Reserve Place</a>
                        </Link>


                    </nav>
                </div>
            </div>
        </header>

    );
}

export default Navbar