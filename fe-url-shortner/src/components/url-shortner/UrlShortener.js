import { useState, useEffect } from 'react';
import './UrlShortener.css';

function UrlShortener() {
  const [url, setUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');
  const [loading, setLoading] = useState(false);
  const [inputFocused, setInputFocused] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);
  const [copied, setCopied] = useState(false);

  const copyToClipboard = async () => {
    try {
      await navigator.clipboard.writeText(shortUrl);
      setCopied(true);
      setTimeout(() => setCopied(false), 2000);
    } catch (err) {
      console.error('Failed to copy: ', err);
    }
  };

  useEffect(() => {
    if (shortUrl && !loading) {
      setShowSuccess(true);
      const timer = setTimeout(() => setShowSuccess(false), 3000);
      return () => clearTimeout(timer);
    }
  }, [shortUrl, loading]);

  const getClassName = () => {
    let className = 'url-shortener';
    if (loading) className += ' loading';
    if (showSuccess && !shortUrl.includes('error') && !shortUrl.includes('CORS')) className += ' success';
    if (shortUrl && (shortUrl.includes('error') || shortUrl.includes('CORS'))) className += ' error';
    if (inputFocused) className += ' focused';
    return className;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (url) {
      setLoading(true);
      try {
        const response = await fetch('http://34.247.64.170:8080/api/url', {
          method: 'POST',
          mode: 'cors',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ url })
        });
        const data = await response.json();
        setShortUrl(data.shortUrl || 'Cannot get URL');
      } catch (error) {
        setShortUrl('CORS error - server needs to allow your domain');
      } finally {
        setLoading(false);
      }
    }
  };

  const generateParticles = () => {
    const particles = [];
    for (let i = 0; i < 100; i++) {
      let x, y;
      if (i < 30) {
        // Top corners
        x = Math.random() < 0.5 ? Math.random() * 25 : 75 + Math.random() * 25;
        y = Math.random() * 25;
      } else if (i < 60) {
        // Bottom corners
        x = Math.random() < 0.5 ? Math.random() * 25 : 75 + Math.random() * 25;
        y = 75 + Math.random() * 25;
      } else if (i < 80) {
        // Behind component (center area)
        x = 25 + Math.random() * 50;
        y = 25 + Math.random() * 50;
      } else {
        // Random positions
        x = Math.random() * 100;
        y = Math.random() * 100;
      }
      
      particles.push(
        <div 
          key={i} 
          className="particle" 
          style={{
            left: `${x}%`,
            top: `${y}%`,
            animationDelay: `${(i * 0.1) % 5}s`
          }}
        />
      );
    }
    return particles;
  };

  return (
    <div className="container">
      <div className="particles">
        {generateParticles()}
      </div>
      <div className={getClassName()}>
      <h2>URL Shortener</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="url"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          onFocus={() => setInputFocused(true)}
          onBlur={() => setInputFocused(false)}
          placeholder="Enter URL to shorten"
          required
          className="url-input"
        />
        <button type="submit" disabled={loading} className={!loading ? "submit-button":"submit-button-disable"}>
          {loading ? 'Loading...' : 'Shorten URL'}
        </button>
      </form>
      {loading && (
        <div className="loading-text">
          <span className="dinosaur">🦕</span> Loading...
        </div>
      )}
      {shortUrl && !loading && (
        <div className="result-container">
          {
            !shortUrl.includes("CORS error")
            ?
            <>
            <strong>Short URL : </strong> 
            <a href={shortUrl} target="_blank" rel="noopener noreferrer">{shortUrl}</a>
            <button onClick={copyToClipboard} className="copy-button">
              {copied ? '✓ Copied!' : '📋 Copy'}
            </button>
            </>
            :
            <strong className='error-block'>Error : {shortUrl}</strong>
          }

        </div>
      )}
      </div>

    </div>
  );
}

export default UrlShortener;