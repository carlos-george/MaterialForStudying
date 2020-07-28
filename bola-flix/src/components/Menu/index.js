import React from 'react';

import logo from '../../assets/bolaflix.png';
import ButtonLink from './components/ButtonLink';
import './styles.css';
import Button from '../Button';

const Menu = () => {
    return (
        <nav className="menu">
            <a href="/">
                <img className="logo" src={logo} alt="bolaFlix"/>
            </a>

            <Button href="/" >
                Novo Video
            </Button>

        </nav>
    );
}

export default Menu;