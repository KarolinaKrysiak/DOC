import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

test('renders VIA Stories header', () => {
  render(<App />);
  const headerElement = screen.getByText(/VIA Stories/i);
  expect(headerElement).toBeInTheDocument();
});