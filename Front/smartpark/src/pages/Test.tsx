// @ts-ignore
import Sato from '../assets/imgs/Sato.png'
import './css/main.css'
const test = () => {

    return (
        <div className="card mb-44 mt-44">
            <div className="card-left">
                <header className="header">
                    <img src={Sato}/>
                    <h5>WORLD CUP 2030</h5>
                </header>
                <main>
                    <div className="barcode">
                        <img src="https://www.freepnglogos.com/uploads/barcode-png/barcode-laser-code-vector-graphic-pixabay-3.png" />
                    </div>
                    <section className="main-details">
                        <p><span className="heading"> CITY</span><br/>Safi</p>
                        <div className="centered">
                            <p><span className="heading"> Stadium name</span><br />ElMasira </p> &#8594;
                            <p><span className="heading"> Date</span><br /> 2030/12/12</p>
                        </div>
                        <div className="flight-details">
                            <p><span className="heading"> TIME</span><br/>10:00</p>
                            <p><span className="heading"> Gate</span><br/>G04</p>
                            <p><span className="heading"> Seats</span><br/>32k</p>
                        </div>
                    </section>
                </main>
            </div>
            <div className="card-right">
                <header className="header">
                    <img src={Sato}/>
                </header>
                <main className="main">
                    <div className="centered">
                        <p><span className="heading"> CITY</span><br/>Safi</p>
                        <p><span className="heading"> Seats</span><br/>32k</p>
                    </div>
                    <div className="centered">
                        <p><span className="heading"> Stadium name</span><br />Elmasira </p> &#8594;
                    </div>
                    <div className="centered">
                        <p><span className="heading"> DATE</span><br />2030/12/12 </p>
                        <p><span className="heading"> TIME</span><br /> 10:00</p>
                    </div>
                </main>
            </div>
        </div>
    )
}

export default test