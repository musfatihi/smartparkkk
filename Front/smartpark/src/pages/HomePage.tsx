import t1 from '../assets/imgs/t1.jpg';
import t2 from '../assets/imgs/t2.jpg';
import hakimi from '../assets/imgs/hakimi.jpg';
import maroc from '../assets/imgs/marocwin.jpg';
import parking_ from '../assets/imgs/parking/parking_.jpg';

function home() {
    return (
        <div>
            <div className="container flex flex-col items-center px-6 py-12 mx-auto lg:flex-row">
                <div className="lg:w-1/2">
                    <h1 className="max-w-xl font-serif text-4xl font-medium tracking-wide text-[#343D33] capitalize md:text-6xl ">A beatiful adventure awaits</h1>

                    <p className="max-w-lg mt-4 text-gray-500">Lorem ipsum dolor sit amet consectetur adipisicing elit. At magnam voluptatibus perferendis odit optio.</p>

                    <div className="mt-6 sm:flex sm:items-center">
                        <a href="#" className="bg-[#475F45] hover:bg-[#475F45]/80 duration-300 transition-colors border-2 border-[#475F45] px-6 block text-center py-3 uppercase text-sm font-bold leading-4 tracking-widest text-white ">
                            Book Your Place
                        </a>

                        <a href="#" className="border-2 text-sm duration-300 transition-colors hover:bg-[#475F45] hover:text-white font-bold leading-4 mt-4 sm:mt-0 tracking-widest text-[#475F45] sm:mx-4 border-[#475F45] px-6 block text-center py-3 uppercase">
                            Learn More
                        </a>
                    </div>
                </div>

                <div className="h-[38rem] mt-12 lg:mt-0 w-full mx-auto max-w-md overflow-hidden rounded-t-full outline outline-4 outline-offset-4 outline-[#475F45]/40">
                    <img className="object-cover w-full h-full rounded-t-full " src={parking_} alt="main page" />
                </div>
            </div>

            <section className="container px-6 py-12 mx-auto lg:py-16">
                <h3 className="font-serif text-3xl text-[#343D33] capitalize md:text-4xl lg:text-5xl">
                    News & Updates
                </h3>

                <div className="mt-8 xl:-mx-6 xl:flex">
                    <div className="xl:w-1/2 xl:mx-6">
                        <img className="order-1 object-cover lg:order-2 w-ful h-72 lg:h-96 lg:mt-12" src={t1} alt="main page" />

                        <h2 className="mt-6 font-serif text-3xl font-medium text-gray-700">Plants Around Us</h2>

                        <p className="mt-2 text-gray-500">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Tincidunt facilisis nuncLorem ipsum dolor sit.Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>

                        <p className="mt-4 italic text-gray-600">December 23, 2021</p>
                    </div>

                    <div className="mt-8 space-y-8 xl:w-1/2 xl:mx-6 xl:mt-0">
                        <div className="md:-mx-4 md:flex md:items-center">
                            <img  className="object-cover w-full h-56 md:h-48 md:mx-4 md:w-80 shrink-0" src={t2} alt="main page" />

                            <div className="mt-6 md:mx-4 md:mt-0">
                                <h2 className="font-serif text-2xl font-medium text-gray-700 ">Lush Gardens</h2>

                                <p className="mt-2 text-gray-500">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Tincidunt facilisis nuncLorem ipsum dolor sit...</p>

                                <p className="mt-4 italic text-gray-600">December 16, 2021</p>
                            </div>
                        </div>

                        <div className="md:-mx-4 md:flex md:items-center">
                            <img  className="object-cover w-full h-56 md:h-48 md:mx-4 md:w-80 shrink-0" src={hakimi} alt="main page" />

                            <div className="mt-6 md:mx-4 md:mt-0">
                                <h2 className="font-serif text-2xl font-medium text-gray-700 ">Exotic Nature</h2>

                                <p className="mt-2 text-gray-500">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Tincidunt facilisis nuncLorem ipsum dolor sit...</p>

                                <p className="mt-4 italic text-gray-600">November 11, 2021</p>
                            </div>
                        </div>

                        <div className="md:-mx-4 md:flex md:items-center">
                            <img  className="object-cover w-full h-56 md:h-48 md:mx-4 md:w-80 shrink-0" src={maroc} alt="main page" />

                            <div className="mt-6 md:mx-4 md:mt-0">
                                <h2 className="font-serif text-2xl font-medium text-gray-700 ">It Starts with Soil</h2>

                                <p className="mt-2 text-gray-500">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Tincidunt facilisis nuncLorem ipsum dolor sit...</p>

                                <p className="mt-4 italic text-gray-600">November 3, 2021</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default home