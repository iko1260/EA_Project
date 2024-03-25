import React, { useState, useEffect } from 'react';
import './Player.css';

const Player = () => {
    const [position, setPosition] = useState({ x: 0, y: 0 });
    const [targetPosition, setTargetPosition] = useState({ x: 0, y: 0 });

    const movePlayer = () => {
        setPosition((prevPosition) => {
            const diffX = targetPosition.x - prevPosition.x;
            const diffY = targetPosition.y - prevPosition.y;

            const speed = 0.1; // Adjust speed here
            const nextPosition = {
                x: Math.abs(diffX) > speed ? prevPosition.x + Math.sign(diffX) * speed : targetPosition.x,
                y: Math.abs(diffY) > speed ? prevPosition.y + Math.sign(diffY) * speed : targetPosition.y,
            };

            return nextPosition;
        });
    };

    useEffect(() => {
        const handleKeyDown = (event) => {
            let newPosition = { ...position };

            switch (event.key) {
                case 'ArrowUp':
                    newPosition.y -= 1;
                    break;
                case 'ArrowDown':
                    newPosition.y += 1;
                    break;
                case 'ArrowLeft':
                    newPosition.x -= 1;
                    break;
                case 'ArrowRight':
                    newPosition.x += 1;
                    break;
                default:
                    break;
            }

            setTargetPosition(newPosition);
        };

        window.addEventListener('keydown', handleKeyDown);

        return () => {
            window.removeEventListener('keydown', handleKeyDown);
        };
    }, [position]);

    useEffect(() => {
        const animationFrame = requestAnimationFrame(movePlayer);

        return () => {
            cancelAnimationFrame(animationFrame);
        };
    }, [targetPosition]);

    return (
        <div className="player" style={{ left: position.x * 50, top: position.y * 50 }}></div>
    );
};

export default Player;
